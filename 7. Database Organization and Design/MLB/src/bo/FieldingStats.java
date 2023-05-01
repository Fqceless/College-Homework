package bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name="fieldingstats")
public class FieldingStats implements Serializable {

	@Id
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="playerid"),
		@JoinColumn(name="year")
	})
	PlayerSeason id;
	
	@Column
	Integer errors;
	@Column
	Integer putOuts;
	
	public PlayerSeason getId() {
		return id;
	}
	public void setId(PlayerSeason id) {
		this.id = id;
	}

	public Integer getErrors() {
		return errors;
	}
	public void setErrors(Integer errors) {
		this.errors = errors;
	}
	public Integer getPutOuts() {
		return putOuts;
	}
	public void setPutOuts(Integer putOuts) {
		this.putOuts = putOuts;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BattingStats)){
			return false;
		}
		FieldingStats other = (FieldingStats)obj;
		// One-to-One with PlayerSeason so this works 
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
}
