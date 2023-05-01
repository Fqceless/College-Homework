package bo;

import java.util.HashSet;
import java.util.Set;

public class PlayerCareerStats {

	Player player;
	Set<PlayerSeason> seasons = new HashSet<PlayerSeason>();
	// general info
	Integer gamesPlayed;
	Double salary;
	// batting stats
	Integer atBats;
	Integer hits;
	Integer doubles;
	Integer triples;
	Integer homeRuns;
	Integer runsBattedIn;
	Integer strikeoutsBatting;
	Integer walksBatting;
	Integer hitByPitch;
	Integer intentionalWalks;
	Integer steals;
	Integer stealsAttempted;
	// catching stats
	Integer passedBalls;
	Integer wildPitchesCatching;
	Integer stealsAllowed;
	Integer stealsCaught;
	// fielding stats
	Integer errors;
	Integer putOuts;
	// pitching stats
	Integer outsPitched;
	Integer earnedRunsAllowed;
	Integer homeRunsAllowed;
	Integer strikeoutsPitching;
	Integer walksPitching;
	Integer wins;
	Integer losses;
	Integer wildPitchesPitching;
	Integer battersFaced;
	Integer hitBatters;
	Integer saves;
	
	// constructor
	public PlayerCareerStats(Player p) {
		this.player = p;
		this.seasons = p.getSeasons();
	}

	// derived stats
	public Double getBattingAverage() {
		return this.getHits()/this.getAtBats().doubleValue();
	}
	
	public Double getERA() {
		return this.getEarnedRunsAllowed()/this.getOutsPitched().doubleValue();
	}
	
	// getters
	public Player getPlayer() {
		return player;
	}
	// lazy load all career stats
	public Integer getGamesPlayed() {
		if (gamesPlayed == null) {
			gamesPlayed=0;
			for (PlayerSeason s:seasons) {
				gamesPlayed += s.getGamesPlayed();
			}
		}
		return gamesPlayed;
	}
	
	public Double getSalary() {
		if (salary == null) {
			salary=0.0;
			for (PlayerSeason s:seasons) {
				salary += s.getSalary();
			}
		}
		return salary;
	}
	
	public Integer getAtBats() {
		if (atBats == null) {
			atBats=0;
			for (PlayerSeason s:seasons) {
				atBats += s.getBattingStats().getAtBats();
			}
		}
		return atBats;
	}

	public Integer getHits() {
		if (hits == null) {
			hits=0;
			for (PlayerSeason s:seasons) {
				hits += s.getBattingStats().getHits();
			}
		}
		return hits;
	}

	public Integer getDoubles() {
		if (doubles == null) {
			doubles=0;
			for (PlayerSeason s:seasons) {
				doubles += s.getBattingStats().getDoubles();
			}
		}
		return doubles;
	}

	public Integer getTriples() {
		if (triples == null) {
			triples=0;
			for (PlayerSeason s:seasons) {
				triples += s.getBattingStats().getTriples();
			}
		}
		return triples;
	}

	public Integer getHomeRuns() {
		if (homeRuns == null) {
			homeRuns=0;
			for (PlayerSeason s:seasons) {
				homeRuns += s.getBattingStats().getHomeRuns();
			}
		}
		return homeRuns;
	}

	public Integer getRunsBattedIn() {
		if (runsBattedIn == null) {
			runsBattedIn=0;
			for (PlayerSeason s:seasons) {
				runsBattedIn += s.getBattingStats().getRunsBattedIn();
			}
		}
		return runsBattedIn;
	}

	public Integer getStrikeoutsBatting() {
		if (strikeoutsBatting == null) {
			strikeoutsBatting=0;
			for (PlayerSeason s:seasons) {
				strikeoutsBatting += s.getBattingStats().getStrikeouts();
			}
		}
		return strikeoutsBatting;
	}

	public Integer getWalksBatting() {
		if (walksBatting == null) {
			walksBatting=0;
			for (PlayerSeason s:seasons) {
				walksBatting += s.getBattingStats().getWalks();
			}
		}
		return walksBatting;
	}

	public Integer getHitByPitch() {
		if (hitByPitch == null) {
			hitByPitch=0;
			for (PlayerSeason s:seasons) {
				hitByPitch += s.getBattingStats().getHitByPitch();
			}
		}
		return hitByPitch;
	}

	public Integer getIntentionalWalks() {
		if (intentionalWalks == null) {
			intentionalWalks=0;
			for (PlayerSeason s:seasons) {
				intentionalWalks += s.getBattingStats().getIntentionalWalks();
			}
		}
		return intentionalWalks;
	}

	public Integer getSteals() {
		if (steals == null) {
			steals=0;
			for (PlayerSeason s:seasons) {
				steals += s.getBattingStats().getSteals();
			}
		}
		return steals;
	}

	public Integer getStealsAttempted() {
		if (stealsAttempted == null) {
			stealsAttempted=0;
			for (PlayerSeason s:seasons) {
				stealsAttempted += s.getBattingStats().getStealsAttempted();
			}
		}
		return stealsAttempted;
	}

	public Integer getPassedBalls() {
		if (passedBalls == null) {
			passedBalls=0;
			for (PlayerSeason s:seasons) {
				passedBalls += s.getCatchingStats().getPassedBalls();
			}
		}
		return passedBalls;
	}

	public Integer getWildPitchesCatching() {
		if (wildPitchesCatching == null) {
			wildPitchesCatching=0;
			for (PlayerSeason s:seasons) {
				wildPitchesCatching += s.getCatchingStats().getWildPitches();
			}
		}
		return wildPitchesCatching;
	}

	public Integer getStealsAllowed() {
		if (stealsAllowed == null) {
			stealsAllowed=0;
			for (PlayerSeason s:seasons) {
				stealsAllowed += s.getCatchingStats().getStealsAllowed();
			}
		}
		return stealsAllowed;
	}

	public Integer getStealsCaught() {
		if (stealsCaught == null) {
			stealsCaught=0;
			for (PlayerSeason s:seasons) {
				stealsCaught += s.getCatchingStats().getStealsAllowed();
			}
		}
		return stealsCaught;
	}

	public Integer getErrors() {
		if (errors == null) {
			errors=0;
			for (PlayerSeason s:seasons) {
				errors += s.getFieldingStats().getErrors();
			}
		}
		return errors;
	}

	public Integer getPutOuts() {
		if (putOuts == null) {
			putOuts=0;
			for (PlayerSeason s:seasons) {
				putOuts += s.getFieldingStats().getPutOuts();
			}
		}
		return putOuts;
	}

	public Integer getOutsPitched() {
		if (outsPitched == null) {
			outsPitched=0;
			for (PlayerSeason s:seasons) {
				outsPitched += s.getPitchingStats().getOutsPitched();
			}
		}
		return outsPitched;
	}

	public Integer getEarnedRunsAllowed() {
		if (earnedRunsAllowed == null) {
			earnedRunsAllowed=0;
			for (PlayerSeason s:seasons) {
				earnedRunsAllowed += s.getPitchingStats().getEarnedRunsAllowed();
			}
		}
		return earnedRunsAllowed;
	}

	public Integer getHomeRunsAllowed() {
		if (homeRunsAllowed == null) {
			homeRunsAllowed=0;
			for (PlayerSeason s:seasons) {
				homeRunsAllowed += s.getPitchingStats().getHomeRunsAllowed();
			}
		}
		return homeRunsAllowed;
	}

	public Integer getStrikeoutsPitching() {
		if (strikeoutsPitching == null) {
			strikeoutsPitching=0;
			for (PlayerSeason s:seasons) {
				strikeoutsPitching += s.getPitchingStats().getStrikeouts();
			}
		}
		return strikeoutsPitching;
	}

	public Integer getWalksPitching() {
		if (walksPitching == null) {
			walksPitching=0;
			for (PlayerSeason s:seasons) {
				walksPitching += s.getPitchingStats().getWalks();
			}
		}
		return walksPitching;
	}

	public Integer getWins() {
		if (wins == null) {
			wins=0;
			for (PlayerSeason s:seasons) {
				wins += s.getPitchingStats().getWins();
			}
		}
		return wins;
	}

	public Integer getLosses() {
		if (losses == null) {
			losses=0;
			for (PlayerSeason s:seasons) {
				losses += s.getPitchingStats().getLosses();
			}
		}
		return losses;
	}

	public Integer getWildPitchesPitching() {
		if (wildPitchesPitching == null) {
			wildPitchesPitching=0;
			for (PlayerSeason s:seasons) {
				wildPitchesPitching += s.getPitchingStats().getWildPitches();
			}
		}
		return wildPitchesPitching;
	}

	public Integer getBattersFaced() {
		if (battersFaced == null) {
			battersFaced=0;
			for (PlayerSeason s:seasons) {
				battersFaced += s.getPitchingStats().getBattersFaced();
			}
		}
		return battersFaced;
	}

	public Integer getHitBatters() {
		if (hitBatters == null) {
			hitBatters=0;
			for (PlayerSeason s:seasons) {
				hitBatters += s.getPitchingStats().getHitBatters();
			}
		}
		return hitBatters;
	}

	public Integer getSaves() {
		if (saves == null) {
			saves=0;
			for (PlayerSeason s:seasons) {
				saves += s.getPitchingStats().getSaves();
			}
		}
		return saves;
	}
	
}
