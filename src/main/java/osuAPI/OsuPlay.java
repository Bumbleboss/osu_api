package osuAPI;

import osuAPI.OsuAPI.OsuMod;
import osuAPI.OsuAPI.OsuMode;

public class OsuPlay {
	
	private String beatmap_id;
	
	public String getBeatmapId() { return this.beatmap_id; }
	public void setBeatmapId(String beatmap_id) { this.beatmap_id = beatmap_id; }
	
	private String score;
	
	public String getScore() { return this.score; }
	public void setScore(String score) { this.score = score; }
	
	private String user_id;
	
	public String getUserId() { return this.user_id; }
	public void setUserId(String user_id) { this.user_id = user_id; }
	
	private String max_combo;
	
	public String getMaxCombo() { return this.max_combo; }
	public void setMaxCombo(String max_combo) { this.max_combo = max_combo; }
	
	private String count_50;
	
	public String getCount50() { return this.count_50; }
	public void setCount50(String count_50) { this.count_50 = count_50; }
	
	private String count_100;
	
	public String getCount100() { return this.count_100; }
	public void setCount100(String count_100) { this.count_100 = count_100; }
	
	private String count_300;
	
	public String getCount300() { return this.count_300; }
	public void setCount300(String count_300) { this.count_300 = count_300; }
	
	private String count_miss;
	
	public String getCountMiss() { return this.count_miss; }
	public void setCountMiss(String count_miss) { this.count_miss = count_miss; }
	
	private String count_katu;
	
	public String getCountKatu() { return this.count_katu; }
	public void setCountKatu(String count_katu) { this.count_katu = count_katu; }
	
	private String count_geki;
	
	public String getCountGeki() { return this.count_geki; }
	public void setCountGeki(String count_geki) { this.count_geki = count_geki; }
	
	private String perfect;
	
	public boolean isPerfect() { return this.perfect.equals("1"); }
	public void setPerfect(String perfect) { this.perfect = perfect; }
	
	private OsuMod[] enabled_mods;
	
	public OsuMod[] getMods() { return this.enabled_mods; }
	public void setMods(long raw) { this.enabled_mods = OsuMod.getMods(raw).toArray(new OsuMod[0]); }
	
	private String date;
	
	public String getDate() { return this.date; }
	public void setDate(String date) { this.date = date; }
	
	private String rank;
	
	public String getRank() { return this.rank; }
	public void setRank(String rank) { this.rank = rank; }
	
	private String pp;
	
	public String getPP() { return this.pp; }
	public void setPP(String pp) { this.pp = pp; }
	
	private OsuMode mode;
	
	public OsuMode getMode() { return this.mode; }
	public void setMode(int mode_id) { this.mode = OsuMode.fromId(mode_id); }
	
}