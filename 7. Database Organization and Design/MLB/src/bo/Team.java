package bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("serial")
@Entity(name = "team")
public class Team implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer teamId;
	
	@Column
	String name;
	@Column
	String league;
	@Column
	Integer yearFounded;
	@Column
	Integer yearLast;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="id.team")
	@Fetch(FetchMode.JOIN)
	Set<TeamSeason> seasons = new HashSet<TeamSeason>();
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Team)){
			return false;
		}
		Team other = (Team)obj;
		return (this.name.equalsIgnoreCase(other.name) &&
				this.yearFounded==other.yearFounded);
	}
	 
	@Override
	public int hashCode() {
		Integer hash = 0;
		if (this.name != null) hash += this.name.hashCode();
		if (this.yearFounded != null) hash += this.yearFounded.hashCode();
		return hash;
	}
	
	public Integer getId() {
		return teamId;
	}
	public void setId(Integer id) {
		this.teamId = id;
	}
	
	public void addSeason(TeamSeason t) {
		seasons.add(t);
	}

	public Set<TeamSeason> getSeasons() {
		return seasons;
	}
	
	public TeamSeason getSeason(int year) {
		for (TeamSeason ts: seasons) {
			if (ts.getYear().equals(year)) return ts;
		}
		return null;
	}
	
	public void setSeasons(Set<TeamSeason> seasons) {
		this.seasons = seasons;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(Integer yearFounded) {
		this.yearFounded = yearFounded;
	}
	
	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public Integer getYearLast() {
		return yearLast;
	}

	public void setYearLast(Integer yearLast) {
		this.yearLast = yearLast;
	}


}
