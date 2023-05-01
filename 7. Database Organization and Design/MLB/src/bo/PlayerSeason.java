package bo;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name = "playerseason")
public class PlayerSeason implements Serializable {

	@EmbeddedId
	PlayerSeasonId id;

	@Embeddable
	static class PlayerSeasonId implements Serializable {
		@ManyToOne
		@JoinColumn(name = "playerid", referencedColumnName = "playerid", insertable = false, updatable = false)
		Player player;
		@Column(name="year")
		Integer playerYear;
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof PlayerSeasonId)){
				return false;
			}
			PlayerSeasonId other = (PlayerSeasonId)obj;
			// in order for two different object of this type to be equal,
			// they must be for the same year and for the same player
			return (this.player==other.player &&
					this.playerYear==other.playerYear);
		}
		 
		@Override
		public int hashCode() {
			Integer hash = 0;
			if (this.player != null) hash += this.player.hashCode();
			if (this.playerYear != null) hash += this.playerYear.hashCode();
			return hash;
		}
	}

	@Column
	int gamesPlayed;
	@Column
	double salary;

	@OneToOne(cascade=CascadeType.ALL, mappedBy="id")
	BattingStats battingStats;
	@OneToOne(cascade=CascadeType.ALL, mappedBy="id")
	FieldingStats fieldingStats;
	@OneToOne(cascade=CascadeType.ALL, mappedBy="id")
	PitchingStats pitchingStats;
	@OneToOne(cascade=CascadeType.ALL, mappedBy="id")
	CatchingStats catchingStats;
	
	// Hibernate needs a default constructor
	public PlayerSeason() {}
	
	public PlayerSeason(Player p, Integer year) {
		PlayerSeasonId psi = new PlayerSeasonId();
		psi.player = p;
		psi.playerYear = year;
		this.id = psi;
	}
	
	// derived stats
	public Double getBattingAverage() {
		Double batave = 0.0;
		if (battingStats != null) {
			if (battingStats.getHits() != null &&
				battingStats.getAtBats() != null) {
				batave = battingStats.getHits()/battingStats.getAtBats().doubleValue();
			}
		}
		return batave;
	}

	public BattingStats getBattingStats() {
		return battingStats;
	}

	public void setBattingStats(BattingStats battingStats) {
		this.battingStats = battingStats;
	}
	
	public FieldingStats getFieldingStats() {
		return fieldingStats;
	}

	public void setFieldingStats(FieldingStats fieldingStats) {
		this.fieldingStats = fieldingStats;
	}
	
	public PitchingStats getPitchingStats() {
		return pitchingStats;
	}

	public void setPitchingStats(PitchingStats pitchingStats) {
		this.pitchingStats = pitchingStats;
	}
	
	public CatchingStats getCatchingStats() {
		return catchingStats;
	}

	public void setCatchingStats(CatchingStats catchingStats) {
		this.catchingStats = catchingStats;
	}

	public void setYear(Integer year) {
		this.id.playerYear = year;
	}

	public Integer getYear() {
		return this.id.playerYear;
	}

	public Player getPlayer() {
		return this.id.player;
	}

	public void setPlayer(Player player) {
		this.id.player = player;
	}

	public PlayerSeasonId getId() {
		return this.id;
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PlayerSeason)){
			return false;
		}
		PlayerSeason other = (PlayerSeason)obj;
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	public static Comparator<PlayerSeason> playerSeasonsComparator = new Comparator<PlayerSeason>() {

		public int compare(PlayerSeason ps1, PlayerSeason ps2) {
			Integer year1 = ps1.getYear();
			Integer year2 = ps2.getYear();
			return year1.compareTo(year2);
		}

	};
	
}
