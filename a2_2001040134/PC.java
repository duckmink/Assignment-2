package a2_2001040134;


import java.util.HashSet;
import java.util.Vector;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview PC is the object that have its model, year,
 *           manufacturer, and its comps (components)
 * @attributes
 *             model String String
 *             year Integer int
 *             manName String String
 *             comps Set<String> Set<String>
 * @object A typical PC is c=<mod,y,mn,comps>, where model(mod), year(y),
 *         manName(mn), comps(comps),
 *         mutable(model) = true /\ optional(model) = false /\ length(model) =
 *         20 /\
 *         mutable(year) = false /\ optional(year) = false /\ min(year) = 1984
 *         /\
 *         mutable(manName) = false /\ optional(manName) = false /\
 *         length(manName) = 15 /\
 *         mutable(comps) = true /\ optional(comps) = false
 * @author Minh
 * 
 */
public class PC {

	@DomainConstraint(mutable = true, optional = false)
	private String model;

	@DomainConstraint(mutable = false, optional = false, min = MIN_YEAR)
	private int year;

	@DomainConstraint(mutable = false, optional = false)
	private String manName;

	@DomainConstraint(mutable = true, optional = false)
	private Set<String> comps;

	// Constants
	private static final int MIN_YEAR = 1984;
	private static final int LENGTH_MODEL = 20;
	private static final int LENGTH_MAN = 15;

	/**
	 * @effects
	 *          if model, year, manName, comps are valid
	 *          initialize this as <m, y, mn, comps>
	 *          else
	 *          throws NotPossibleException
	 */
	public PC(@AttrRef("model") String m, @AttrRef("year") int y, @AttrRef("manName") String mn,
			@AttrRef("comps") Set<String> comps) throws NotPossibleException {

		if (validateModel(m)) {
			this.model = m;
		} else {
			System.out.println("PC init: Invalid model");
		}
		if (validateYear(y)) {
			this.year = y;
		} else {
			System.out.println("PC init: Invalid year");
		}
		if (validateManName(mn)) {
			this.manName = mn;
		} else {
			System.out.println("PC init: Invalid manufacturer name");
		}
		if (validateComps(comps)) {
			this.comps = comps;
		} else {
			System.out.println("PC init: Invalid components");
		}
	}

	@DOpt(type = OptType.Mutator)
	@AttrRef("model")
	public void setModel(String m) {
		this.model = m;
	}

	@DOpt(type = OptType.Mutator)
	@AttrRef("year")
	public void setYear(int y) {
		this.year = y;
	}

	@DOpt(type = OptType.Mutator)
	@AttrRef("manName")
	public void setManName(String mn) {
		this.manName = mn;
	}

	@DOpt(type = OptType.Mutator)
	@AttrRef("comps")
	public void setComps(Set<String> comps) {
		this.comps = comps;
	}

	@DOpt(type = OptType.Observer)
	@AttrRef("model")
	public String getModel() {
		String s = this.model;
		return s;
	}

	@DOpt(type = OptType.Observer)
	@AttrRef("year")
	public int getYear() {
		int s = this.year;
		return s;
	}

	@DOpt(type = OptType.Observer)
	@AttrRef("manName")
	public String getManName() {
		String s = this.manName;
		return s;
	}

	@DOpt(type = OptType.Observer)
	@AttrRef("comps")
	public Set<String> getComps() {
		Set<String> c = this.comps;
		return c;
	}

	/**
	 * @effects
	 *          if mod is valid
	 *          return true
	 *          else
	 *          return false
	 */
	private boolean validateModel(String mod) {
		if (mod != null && mod.length() > 0 && mod.length() <= LENGTH_MODEL) {
			return true;
		} else
			return false;
	}

	/**
	 * @effects
	 *          if y is valid
	 *          return true
	 *          else
	 *          return false
	 */
	private boolean validateYear(int y) {
		if (y >= MIN_YEAR) {
			return true;
		} else
			return false;
	}

	/**
	 * @effects
	 *          if mn is valid
	 *          return true
	 *          else
	 *          return false
	 */
	private boolean validateManName(String mn) {
		if (mn != null && mn.length() > 0 && mn.length() <= LENGTH_MAN) {
			return true;
		} else
			return false;
	}

	/**
	 * @effects
	 *          if comps is valid
	 *          return true
	 *          else
	 *          return false
	 */
	private boolean validateComps(Set<String> comps) {
		if (comps != null) {
			return true;
		} else
			return false;
	}

	/**
	 * @effects
	 *          if this satisfies abstract properties
	 *          return true
	 *          else
	 *          return false
	 */
	public boolean repOK() {
		if (!this.validateManName(this.manName) || !this.validateYear(this.year) || !this.validateModel(this.model)
				|| !this.validateComps(this.comps))
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		String out = "";
		StringBuffer s = new StringBuffer(99);
		s.append("PC<");
		s.append(this.model);
		s.append(",");
		s.append(this.year);
		s.append(",");
		s.append(this.manName);
		s.append(",");
		s.append(this.comps.toString());
		s.append(">");
		out += s.toString();
		return out;
	}

	@Override
	@DOpt(type = OptType.Default)
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PC))
			return false;
		PC pc = (PC) o;
		return (this.year == pc.year &&
				this.model.equals(pc.model) &&
				this.manName.equals(pc.manName) &&
				this.equalsComps(pc));
	}

	public boolean equalsComps(Object o) {
		Set<String> srcComps = this.getComps();
		Vector<String> src = srcComps.getElements();
		HashSet<String> s = new HashSet<>();
		for (String e : src) {
			s.add(e);
		}
		Set<String> tgtComps = ((PC) o).getComps();
		Vector<String> tgt = tgtComps.getElements();
		HashSet<String> t = new HashSet<>();
		for (String e : tgt) {
			t.add(e);
		}
		return t.equals(s);

	}
}
