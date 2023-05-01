package bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name="catchingstats")
public class CatchingStats implements Serializable{
	
	@Id
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="playerid"),
		@JoinColumn(name="year")
	})
	PlayerSeason id;
	
	@Column
	Integer passedBalls;
	@Column
	Integer wildPitches;
	@Column
	Integer stealsAllowed;
	@Column
	Integer stealsCaught;
	
	public PlayerSeason getId() {
		return id;
	}
	public void setId(PlayerSeason id) {
		this.id = id;
	}

	public Integer getPassedBalls() {
		return passedBalls;
	}
	public void setPassedBalls(Integer passedBalls) {
		this.passedBalls = passedBalls;
	}
	public Integer getWildPitches() {
		return wildPitches;
	}
	public void setWildPitches(Integer wildPitches) {
		this.wildPitches = wildPitches;
	}
	public Integer getStealsAllowed() {
		return stealsAllowed;
	}
	public void setStealsAllowed(Integer stealsAllowed) {
		this.stealsAllowed = stealsAllowed;
	}
	public Integer getStealsCaught() {
		return stealsCaught;
	}
	public void setStealsCaught(Integer stealsCaught) {
		this.stealsCaught = stealsCaught;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BattingStats)){
			return false;
		}
		CatchingStats other = (CatchingStats)obj;
		// One-to-One with PlayerSeason so this works 
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
}
