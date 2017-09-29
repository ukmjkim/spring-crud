package ga.rugal.sample.core.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

import config.SystemDefaultProperties;
import lombok.Data;

/**
 *
 * @author Rugal Bernstein
 */
@Data
@Entity
@Table(name = "student", schema = SystemDefaultProperties.SCHEMA)
public class Student
{

    @Id
    @Basic(optional = false)
    @Expose
    @NotNull
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Expose
    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Registration> registrationList;

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Student))
        {
            return false;
        }
        Student other = (Student) object;
        return !((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid)));
    }

    @Override
    public String toString()
    {
        return "ga.rugal.student.core.entity.Student[ sid=" + sid + " ]";
    }
}
