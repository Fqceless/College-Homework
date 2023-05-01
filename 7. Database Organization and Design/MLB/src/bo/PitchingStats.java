package bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name="pitchingstats")
public class PitchingStats implements Serializable{

	@Id
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="playerid"),
		@JoinColumn(name="year")
	})
	PlayerSeason id;
	
	@Column
	Integer outsPitched;
	@Column
	Integer earnedRunsAllowed;
	@Column
	Integer homeRunsAllowed;
	@Column
	Integer strikeouts;
	@Column
	Integer walks;
	@Column
	Integer wins;
	@Column
	Integer losses;
	@Column
	Integer wildPitches;
	@Column
	Integer battersFaced;
	@Column
	Integer hitBatters;
	@Column
	Integer saves;
	
	public PlayerSeason getId() {
		return id;
	}
	public void setId(PlayerSeason id) {
		this.id = id;
	}
	
	public Integer getOutsPitched() {
		return outsPitched;
	}
	public void setOutsPitched(Integer outsPitched) {
		this.outsPitched = outsPitched;
	}
	public Integer getEarnedRunsAllowed() {
		return earnedRunsAllowed;
	}
	public void setEarnedRunsAllowed(Integer earnedRunsAllowed) {
		this.earnedRunsAllowed = earnedRunsAllowed;
	}
	public Integer getHomeRunsAllowed() {
		return homeRunsAllowed;
	}
	public void setHomeRunsAllowed(Integer homeRunsAllowed) {
		this.homeRunsAllowed = homeRunsAllowed;
	}
	public Integer getStrikeouts() {
		return strikeouts;
	}
	public void setStrikeouts(Integer strikeouts) {
		this.strikeouts = strikeouts;
	}
	public Integer getWalks() {
		return walks;
	}
	public void setWalks(Integer walks) {
		this.walks = walks;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public Integer getLosses() {
		return losses;
	}
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	public Integer getWildPitches() {
		return wildPitches;
	}
	public void setWildPitches(Integer wildPitches) {
		this.wildPitches = wildPitches;
	}
	public Integer getBattersFaced() {
		return battersFaced;
	}
	public void setBattersFaced(Integer battersFaced) {
		this.battersFaced = battersFaced;
	}
	public Integer getHitBatters() {
		return hitBatters;
	}
	public void setHitBatters(Integer hitBatters) {
		this.hitBatters = hitBatters;
	}
	public Integer getSaves() {
		return saves;
	}
	public void setSaves(Integer saves) {
		this.saves = saves;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BattingStats)){
			return false;
		}
		PitchingStats other = (PitchingStats)obj;
		// One-to-One with PlayerSeason so this works 
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
}
