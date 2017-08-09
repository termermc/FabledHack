package net.termer.fabledhack.api;

import net.termer.fabledhack.FabledHack;
import flands.Adventurer;
import flands.Adventurer.AbilityStat;
import flands.Adventurer.StaminaStat;
import flands.Adventurer.Stat;

public class PlayerStat {
	private int st = StatType.UNKNOWN;
	private Object s = null;
	
	public static PlayerStat getStat(String stat) {
		PlayerStat tmp = null;
		FabledHack fh = new FabledHack();
		if(stat.equalsIgnoreCase("charisma")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getCharisma());
		} else if(stat.equalsIgnoreCase("combat")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getCombat());
		} else if(stat.equalsIgnoreCase("magic")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getMagic());
		} else if(stat.equalsIgnoreCase("sanctity")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getSanctity());
		} else if(stat.equalsIgnoreCase("scouting")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getScouting());
		} else if(stat.equalsIgnoreCase("thievery")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getThievery());
		} else if(stat.equalsIgnoreCase("rank")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getRank());
		} else if(stat.equalsIgnoreCase("stamina")) {
			tmp = new PlayerStat(fh.getGame().getAdventurer().getStamina());
		}
		return tmp;
	}
	public static int getStatId(String stat) {
		int tmp = -1;
		if(stat.equalsIgnoreCase("charisma")) {
			tmp = Adventurer.ABILITY_CHARISMA;
		} else if(stat.equalsIgnoreCase("combat")) {
			tmp = Adventurer.ABILITY_COMBAT;
		} else if(stat.equalsIgnoreCase("magic")) {
			tmp = Adventurer.ABILITY_MAGIC;
		} else if(stat.equalsIgnoreCase("sanctity")) {
			tmp = Adventurer.ABILITY_SANCTITY;
		} else if(stat.equalsIgnoreCase("scouting")) {
			tmp = Adventurer.ABILITY_SCOUTING;
		} else if(stat.equalsIgnoreCase("thievery")) {
			tmp = Adventurer.ABILITY_THIEVERY;
		} else if(stat.equalsIgnoreCase("stamina")) {
			tmp = Adventurer.ABILITY_STAMINA;
		}
		return tmp;
	}
	
	public PlayerStat(Object stat) {
		s = stat;
		if(s instanceof AbilityStat) {
			st = StatType.ABILITY_STAT;
		} else if(s instanceof Stat) {
			st = StatType.RANK;
		} else if(s instanceof StaminaStat) {
			st = StatType.STAMINA;
		}
	}
	public AbilityStat getAbility() {
		AbilityStat tmp = null;
		if(st == StatType.ABILITY_STAT) {
			tmp = (AbilityStat)s;
		}
		return tmp;
	}
	public Stat getRank() {
		Stat tmp = null;
		if(s instanceof Stat) {
			tmp = (Stat)s;
		}
		return tmp;
	}
	public StaminaStat getStamina() {
		StaminaStat tmp = null;
		if(s instanceof StaminaStat) {
			tmp = (StaminaStat)s;
		}
		return tmp;
	}
	public int getStatType() {
		return st;
	}
}
