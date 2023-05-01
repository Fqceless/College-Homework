package bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer playerId;
	
	@ElementCollection
	@CollectionTable(name = "playerposition", joinColumns = @JoinColumn(name = "playerid"))
	@Column(name = "position")
	@Fetch(FetchMode.JOIN)
	Set<String> positions = new HashSet<String>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="id.player")
	@Fetch(FetchMode.JOIN)
	Set<PlayerSeason> seasons = new HashSet<PlayerSeason>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "players")
	Set<TeamSeason> teamSeasons = new HashSet<TeamSeason>();
	
	@Column
	String name;
	@Column
	String givenName;
	@Column
	Date birthDay;
	@Column
	Date deathDay;
	@Column
	String battingHand;
	@Column
	String throwingHand;
	@Column
	String birthCity;
	@Column
	String birthState;
  @Column
	String birthCountry;
	@Column
	Date firstGame;
	@Column
	Date lastGame;

	// utility function
	public PlayerSeason getPlayerSeason(Integer year) {
		for (PlayerSeason ps : seasons) {
			if (ps.getYear().equals(year)) return ps;
		}
		return null;
	}
	
	public Set<TeamSeason> getTeamSeasons() {
		return teamSeasons;
	}
	
	public void setTeamSeasons(Set<TeamSeason> teamSeasons) {
		this.teamSeasons = teamSeasons;
	}
	
	public void addTeamSeason(TeamSeason ts) {
		this.teamSeasons.add(ts);
	}
	
	public void addPosition(String p) {
		positions.add(p);
	}

	public Set<String> getPositions() {
		return positions;
	}

	public void setPositions(Set<String> positions) {
		this.positions = positions;
	}

	public void addSeason(PlayerSeason s) {
		seasons.add(s);
	}

	public Set<PlayerSeason> getSeasons() {
		return seasons;
	}
	
	public PlayerSeason getSeason(int year) {
		for (PlayerSeason ps: seasons) {
			if (ps.getYear().equals(year)) return ps;
		}
		return null;
	}
	
	public void setSeasons(Set<PlayerSeason> seasons) {
		this.seasons = seasons;
	}
	
	public Integer getId() {
		return playerId;
	}
	public void setId(Integer id) {
		this.playerId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String nickName) {
		this.givenName = nickName;
	}

	public String getBattingHand() {
		return battingHand;
	}

	public void setBattingHand(String battingHand) {
		this.battingHand = battingHand;
	}

	public String getThrowingHand() {
		return throwingHand;
	}

	public void setThrowingHand(String throwingHand) {
		this.throwingHand = throwingHand;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthState() {
		return birthState;
	}

	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}
  
  public String getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public Date getFirstGame() {
		return firstGame;
	}

	public void setFirstGame(Date firstGame) {
		this.firstGame = firstGame;
	}

	public Date getLastGame() {
		return lastGame;
	}

	public void setLastGame(Date lastGame) {
		this.lastGame = lastGame;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Date getDeathDay() {
		return deathDay;
	}

	public void setDeathDay(Date deathDay) {
		this.deathDay = deathDay;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Player)){
			return false;
		}
		Player other = (Player) obj;
		return (this.getName().equalsIgnoreCase(other.getName()) &&
				this.getBirthDay()==other.getBirthDay() &&
				this.getDeathDay()==other.getDeathDay());
	}
	 
	@Override
	public int hashCode() {
		Integer hash = 0;
		if (this.getName()!=null) hash += this.getName().hashCode(); 
		if (this.getBirthDay()!=null) hash += this.getBirthDay().hashCode();
		if (this.getDeathDay()!=null) hash += this.getDeathDay().hashCode();
		return hash;
	}
	
	
}
