package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import view.PlayerView;
import bo.Player;
import bo.PlayerCareerStats;
import bo.PlayerSeason;
import bo.Team;
import bo.TeamSeason;
import dataaccesslayer.HibernateUtil;

public class PlayerController extends BaseController {

    @Override
    public void init(String query) {
        System.out.println("building dynamic html for player");
        view = new PlayerView();
        process(query);
    }
    
    @Override
    protected void performAction() {
        String action = keyVals.get("action");
        System.out.println("playercontroller performing action: " + action);
        if (action.equalsIgnoreCase(ACT_SEARCHFORM)) {
            processSearchForm();
        } else if (action.equalsIgnoreCase(ACT_SEARCH)) {
            processSearch();
        } else if (action.equalsIgnoreCase(ACT_DETAIL)) {
            processDetails();
        } 
    }

    protected void processSearchForm() {
        view.buildSearchForm();
    }
    
    protected final void processSearch() {
        String name = keyVals.get("name");
        if (name == null) {
            return;
        }
        String v = keyVals.get("exact");
        boolean exact = (v != null && v.equalsIgnoreCase("on"));
        List<Player> bos = HibernateUtil.retrievePlayersByName(name, exact);
        view.printSearchResultsMessage(name, exact);
        buildSearchResultsTablePlayer(bos);
        view.buildLinkToSearch();
    }

    protected final void processDetails() {
        String id = keyVals.get("id");
        if (id == null) {
            return;
        }
        Player p = (Player) HibernateUtil.retrievePlayerById(Integer.valueOf(id));
        if (p == null) return;
        buildSearchResultsTablePlayerDetail(p);
        view.buildLinkToSearch();
    }

    private void buildSearchResultsTablePlayer(List<Player> bos) {
        // need a row for the table headers
        String[][] table = new String[bos.size() + 1][10];
        table[0][0] = "Id";
        table[0][1] = "Name";
        table[0][2] = "Lifetime Salary";
        table[0][3] = "Games Played";
        table[0][4] = "First Game";
        table[0][5] = "Last Game";
        table[0][6] = "Career Home Runs";
        table[0][7] = "Career Hits";
        table[0][8] = "Career Batting Average";
        table[0][9] = "Career Steals";
        for (int i = 0; i < bos.size(); i++) {
            Player p = bos.get(i);
            PlayerCareerStats stats = new PlayerCareerStats(p);
            String pid = p.getId().toString();
            table[i + 1][0] = view.encodeLink(new String[]{"id"}, new String[]{pid}, pid, ACT_DETAIL, SSP_PLAYER);
            table[i + 1][1] = p.getName();
            table[i + 1][2] = DOLLAR_FORMAT.format(stats.getSalary());
            table[i + 1][3] = stats.getGamesPlayed().toString();
            table[i + 1][4] = formatDate(p.getFirstGame());
            table[i + 1][5] = formatDate(p.getLastGame());
            table[i + 1][6] = stats.getHomeRuns().toString();
            table[i + 1][7] = stats.getHits().toString();
            table[i + 1][8] = DOUBLE_FORMAT.format(stats.getBattingAverage());
            table[i + 1][9] = stats.getSteals().toString();
        }
        view.buildTable(table);
    }
    
    private void buildSearchResultsTablePlayerDetail(Player p) {
    	Set<PlayerSeason> seasons = p.getSeasons();
    	Set<String> positions = p.getPositions();
    	List<PlayerSeason> list = new ArrayList<PlayerSeason>(seasons);
    	Collections.sort(list, PlayerSeason.playerSeasonsComparator);
    	// build 2 tables.  first the player details, then the season details
        // need a row for the table headers
        String[][] playerTable = new String[2][6];
        playerTable[0][0] = "Name";
        playerTable[0][1] = "Given Name";
        playerTable[0][2] = "Positions";
        playerTable[0][3] = "Birthday";
        playerTable[0][4] = "Deathday";
        playerTable[0][5] = "Hometown";
        playerTable[1][0] = p.getName();
        playerTable[1][1] = p.getGivenName();
        String pos="";
        boolean first = true;
        for (String s: positions) {
        	if (first) {
        		pos += s;
        		first = false;
        	} else {
        		pos += ", " + s;	
        	}
        }
        playerTable[1][2] = pos;
        playerTable[1][3] = formatDate(p.getBirthDay());
        playerTable[1][4] = formatDate(p.getDeathDay());
        playerTable[1][5] = p.getBirthCity() + ", " + p.getBirthState();
        
        view.buildTable(playerTable);
        // now for seasons
        String[][] seasonTable = new String[seasons.size()+1][8];
        seasonTable[0][0] = "Year";
        seasonTable[0][1] = "Games Played";
        seasonTable[0][2] = "Salary";
        seasonTable[0][3] = "Team";
        seasonTable[0][4] = "Hits";
        seasonTable[0][5] = "At Bats";
        seasonTable[0][6] = "Batting Average";
        seasonTable[0][7] = "Home Runs";
        int i = 0;
        for (PlayerSeason ps: list) {
        	TeamSeason ts[] = new TeamSeason[ps.getPlayer().getTeamSeasons().size()];
        	ps.getPlayer().getTeamSeasons().toArray(ts);
        	String team = "", tid = "";
        	for (int cnt = 0; cnt < ts.length; cnt++) {
        		if(ts[cnt].getYear().equals(ps.getYear())) {
        			team = ts[cnt].getTeam().getName();
        			tid = ts[cnt].getTeam().getId().toString();
        		}
        	}
        	i++;
        	seasonTable[i][0] = ps.getYear().toString();
        	seasonTable[i][1] = ps.getGamesPlayed().toString();
        	seasonTable[i][2] = DOLLAR_FORMAT.format(ps.getSalary());
        	seasonTable[i][3] = view.encodeLink(new String[]{"id"}, new String[]{tid}, team, ACT_DETAIL, SSP_TEAM);
        	seasonTable[i][4] = ps.getBattingStats().getHits().toString();
        	seasonTable[i][5] = ps.getBattingStats().getAtBats().toString();
        	seasonTable[i][6] = DOUBLE_FORMAT.format(ps.getBattingAverage());
        	seasonTable[i][7] = ps.getBattingStats().getHomeRuns().toString();
        }
        view.buildTable(seasonTable);
    }

}
