package bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name="battingstats") 
public class BattingStats implements Serializable{
	
	@Id
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="playerid"),
		@JoinColumn(name="year")
	})
	PlayerSeason id;
	
	@Column
	Integer atBats;
	@Column
	Integer hits;
	@Column
	Integer doubles;
	@Column
	Integer triples;
	@Column
	Integer homeRuns;
	@Column
	Integer runsBattedIn;
	@Column
	Integer strikeouts;
	@Column
	Integer walks;
	@Column
	Integer hitByPitch;
	@Column
	Integer intentionalWalks;
	@Column
	Integer steals;
	@Column
	Integer stealsAttempted;

	public PlayerSeason getId() {
		return id;
	}
	public void setId(PlayerSeason id) {
		this.id = id;
	}
	
	public Integer getAtBats() {
		return atBats;
	}
	public void setAtBats(Integer atBats) {
		this.atBats = atBats;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getDoubles() {
		return doubles;
	}
	public void setDoubles(Integer doubles) {
		this.doubles = doubles;
	}
	public Integer getTriples() {
		return triples;
	}
	public void setTriples(Integer triples) {
		this.triples = triples;
	}
	public Integer getHomeRuns() {
		return homeRuns;
	}
	public void setHomeRuns(Integer homeRuns) {
		this.homeRuns = homeRuns;
	}
	public Integer getRunsBattedIn() {
		return runsBattedIn;
	}
	public void setRunsBattedIn(Integer runsBattedIn) {
		this.runsBattedIn = runsBattedIn;
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
	public Integer getHitByPitch() {
		return hitByPitch;
	}
	public void setHitByPitch(Integer hitByPitch) {
		this.hitByPitch = hitByPitch;
	}
	public Integer getIntentionalWalks() {
		return intentionalWalks;
	}
	public void setIntentionalWalks(Integer intentionalWalks) {
		this.intentionalWalks = intentionalWalks;
	}
	public Integer getSteals() {
		return steals;
	}
	public void setSteals(Integer steals) {
		this.steals = steals;
	}
	public Integer getStealsAttempted() {
		return stealsAttempted;
	}
	public void setStealsAttempted(Integer stealsAttempted) {
		this.stealsAttempted = stealsAttempted;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BattingStats)){
			return false;
		}
		BattingStats other = (BattingStats)obj;
		// One-to-One with PlayerSeason so this works 
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
		
}
