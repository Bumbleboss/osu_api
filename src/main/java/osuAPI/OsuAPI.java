package osuAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OsuAPI {
	
	private static String API_KEY;
	private static final String BASE = "osu.ppy.sh/api/";
	private static OkHttpClient client = new OkHttpClient();

	public OsuAPI (String key) {
		this.API_KEY = key;
	}

	private static JSONTokener getJSON(String url) {
		Request request = new Request.Builder().url(url).get().build();
		Response response;
		try {
			response = client.newCall(request).execute();
			JSONTokener result = new JSONTokener(response.body().string());
			return result;
		} catch (IOException e) {
			return null;
		}
	}
	public static String getKey() {
		return API_KEY;
	}

	public enum OsuMode {
		STANDARD("Standard", 0),
		TAIKO("Taiko", 1),
		CTB("Ctb", 2),
		MANIA("Mania", 3);
		
		private final String TEXT;
		private final int ID;
		
		private OsuMode(String text, int id) {
			this.TEXT = text;
			this.ID = id;
		}
		
		public String getText() {
			return this.TEXT;
		}
		
		public int getId() {
			return this.ID;
		}
		
		public static OsuMode fromId(int id) {
			for(OsuMode osu_mode : OsuMode.values())
				if(osu_mode.getId() == id)
					return osu_mode;
			
			return null;
		}
	}

	public enum OsuMod {
		NONE("None", 0),
		NO_FAIL("No Fail", 1),
		EASY("Easy", 2),
		NO_VIDEO("No Video", 3),
		HIDDEN("Hidden", 4),
		HARD_ROCK("Hard Rock", 5),
		SUDDEN_DEATH("Sudden Death", 6),
		DOUBLE_TIME("Double Time", 7),
		RELAX("Relax", 8),
		HALF_TIME("Half time", 9),
		NIGHTCORE("Nightcore", 10),
		FLASHLIGHT("Flashlight", 11),
		AUTOPLAY("Autoplay", 12),
		SPUN_OUT("Spun out", 13),
		RELAX_2("Autopilot", 14),
		PERFECT("Perfect", 15),
		KEY_4("Key 4", 16),
		KEY_5("Key 5", 17),
		KEY_6("Key 6", 18),
		KEY_7("Key 7", 19),
		KEY_8("Key 8", 20),
		FADE_IN("Fade In", 21),
		RANDOM("Random", 22),
		LAST_MOD("Last Mod", 23),
		KEY_9("Key 9", 25),
		KEY_10("Key 10", 26),
		KEY_1("Key 1", 27),
		KEY_3("Key 3", 28),
		KEY_2("Key 2", 29);
		
		private final String TEXT;
		private final int ID;
		private final long RAW;
		
		private OsuMod(String text, int id) {
			this.TEXT = text;
			this.ID = id;
			
			this.RAW = 1 << (ID - 1);
		}
		
		public String getText() {
			return this.TEXT;
		}
		
		public int getId() {
			return this.ID;
		}
		
		public long getRaw() {
			return this.RAW;
		}
		
		public static OsuMod fromId(int id) {
			for(OsuMod mod : OsuMod.values())
				if(mod.getId() == id)
					return mod;
			
			return null;
		}
		
		public static long getRaw(OsuMod... mods) {
			long raw = 0;
			
			for(OsuMod mod : mods)
				if(!mod.equals(OsuMod.NONE))
					raw |= mod.getRaw();
			
			return raw;
		}
		
		public static List<OsuMod> getMods(long osu_mods) {
	        List<OsuMod> mods = new LinkedList<>();
	        
	        if(osu_mods == 0) {
        		mods.add(OsuMod.NONE);
        		return mods;
	        }
	        
	        for(OsuMod mod : OsuMod.values()) {
	            if(((osu_mods >> mod.getId() - 1) & 1) == 1)
	                mods.add(mod);
	        }
	        
	        return mods;
		}
		
		public String toString() {
			return this.getText();
		}
	}
	
	public static OsuUser userFromJson(int mode, JSONTokener tokener) {
		OsuUser user = null;
		
		JSONArray array = new JSONArray(tokener);
		if(array.length() > 0) {
			JSONObject object = array.getJSONObject(0);
			
			if(object != null) {
				user = new OsuUser();
				user.setUserId(object.getString("user_id"));
				user.setUsername(object.getString("username"));
				
				if(!object.isNull("count300"))
					user.setCount300(object.getString("count300"));
				else user.setCount300(null);
				
				if(!object.isNull("count100"))
					user.setCount100(object.getString("count100"));
				else user.setCount100(null);
				
				if(!object.isNull("count50"))
					user.setCount50(object.getString("count50"));
				else user.setCount50(null);
				
				if(!object.isNull("playcount"))
					user.setPlayCount(object.getString("playcount"));
				else user.setPlayCount(null);
				
				if(!object.isNull("ranked_score"))
					user.setRankedScore(object.getString("ranked_score"));
				else user.setRankedScore(null);
				
				if(!object.isNull("total_score"))
					user.setTotalScore(object.getString("total_score"));
				else user.setTotalScore(null);
				
				if(!object.isNull("pp_rank"))
					user.setPPRank(object.getString("pp_rank"));
				else user.setPPRank(null);
				
				if(!object.isNull("level"))
					user.setLevel(object.getString("level"));
				else user.setLevel(null);
				
				if(!object.isNull("pp_raw"))
					user.setPPRaw(object.getString("pp_raw"));
				else user.setPPRaw(null);
				
				if(!object.isNull("accuracy"))
					user.setAccuracy(object.getString("accuracy"));
				else user.setAccuracy(null);
				
				if(!object.isNull("count_rank_ss"))
					user.setCountRankSS(object.getString("count_rank_ss"));
				else user.setCountRankSS(null);
				
				if(!object.isNull("count_rank_s"))
					user.setCountRankS(object.getString("count_rank_s"));
				else user.setCountRankS(null);
				
				if(!object.isNull("count_rank_a"))
					user.setCountRankA(object.getString("count_rank_a"));
				else user.setCountRankA(null);
				
				if(!object.isNull("country"))
					user.setCountry(object.getString("country"));
				else user.setCountry(null);
				
				if(!object.isNull("pp_country_rank"))
					user.setPPCountryRank(object.getString("pp_country_rank"));
				else user.setPPCountryRank(null);
				
				user.setMode(mode);
			}
		}
		
		return user;
	}
	
	public static ArrayList<OsuBeatmap> beatmapsFromJson(JSONTokener tokener) {
		ArrayList<OsuBeatmap> beatmaps = new ArrayList<OsuBeatmap>();
		
		JSONArray array = new JSONArray(tokener);
		array.forEach(object -> {
			JSONObject json_object = (JSONObject) object;
			
			OsuBeatmap beatmap = new OsuBeatmap();
			
			beatmap.setApproved(json_object.getString("approved"));
			
			if(!json_object.isNull("approved_date"))
				beatmap.setApprovedDate(json_object.getString("approved_date"));
			else beatmap.setApprovedDate(null);
			
			if(!json_object.isNull("last_updated"))
				beatmap.setLastUpdated(json_object.getString("last_updated"));
			else beatmap.setLastUpdated(null);
			
			if(!json_object.isNull("artist"))
				beatmap.setArtist(json_object.getString("artist"));
			else beatmap.setArtist(null);
			
			if(!json_object.isNull("beatmap_id"))
				beatmap.setBeatmapId(json_object.getString("beatmap_id"));
			else beatmap.setBeatmapId(null);
			
			if(!json_object.isNull("beatmapset_id"))
				beatmap.setBeatmapSetId(json_object.getString("beatmapset_id"));
			else beatmap.setBeatmapSetId(null);

			if(!json_object.isNull("bpm"))
				beatmap.setBPM(json_object.getString("bpm"));
			else beatmap.setBPM(null);
			
			if(!json_object.isNull("creator"))
				beatmap.setCreator(json_object.getString("creator"));
			else beatmap.setCreator(null);
			
			if(!json_object.isNull("difficultyrating"))
				beatmap.setDifficultyRating(json_object.getString("difficultyrating"));
			else beatmap.setDifficultyRating(null);
			
			if(!json_object.isNull("diff_size"))
				beatmap.setDifficultyCircleSize(json_object.getString("diff_size"));
			else beatmap.setDifficultyCircleSize(null);
			
			if(!json_object.isNull("diff_overall"))
				beatmap.setDifficultyOverall(json_object.getString("diff_overall"));
			else beatmap.setDifficultyOverall(null);
			
			if(!json_object.isNull("diff_approach"))
				beatmap.setDifficultyApproachRate(json_object.getString("diff_approach"));
			else beatmap.setDifficultyApproachRate(null);
			
			if(!json_object.isNull("diff_drain"))
				beatmap.setDifficultyHealthDrained(json_object.getString("diff_drain"));
			else beatmap.setDifficultyHealthDrained(null);
			
			if(!json_object.isNull("hit_length"))
				beatmap.setHitLength(json_object.getString("hit_length"));
			else beatmap.setHitLength(null);
			
			if(!json_object.isNull("source"))
				beatmap.setSource(json_object.getString("source"));
			else beatmap.setSource(null);
			
			beatmap.setGenre(json_object.getString("genre_id"));
			
			beatmap.setLanguage(json_object.getString("language_id"));
			
			if(!json_object.isNull("title"))
				beatmap.setTitle(json_object.getString("title"));
			else beatmap.setTitle(null);
			
			if(!json_object.isNull("total_length"))
				beatmap.setTotalLength(json_object.getString("total_length"));
			else beatmap.setTotalLength(null);
			
			if(!json_object.isNull("version"))
				beatmap.setDifficultyName(json_object.getString("version"));
			else beatmap.setDifficultyName(null);
			
			if(!json_object.isNull("file_md5"))
				beatmap.setFileMD5(json_object.getString("file_md5"));
			else beatmap.setFileMD5(null);
			
			beatmap.setMode(Integer.parseInt(json_object.getString("mode")));
			
			if(!json_object.isNull("tags"))
				beatmap.setTags(json_object.getString("tags"));
			else beatmap.setTags(null);
			
			if(!json_object.isNull("favourite_count"))
				beatmap.setFavouriteCount(json_object.getString("favourite_count"));
			else beatmap.setFavouriteCount(null);
			
			if(!json_object.isNull("playcount"))
				beatmap.setPlayCount(json_object.getString("playcount"));
			else beatmap.setPlayCount(null);
			
			if(!json_object.isNull("passcount"))
				beatmap.setPassCount(json_object.getString("passcount"));
			else beatmap.setPassCount(null);
			
			if(!json_object.isNull("max_combo"))
				beatmap.setMaxCombo(json_object.getString("max_combo"));
			else beatmap.setMaxCombo(null);
			
			beatmaps.add(beatmap);
		});
		
		return beatmaps;
	}
	
	public static ArrayList<OsuPlay> playsFromJson(int mode, JSONTokener tokener) {
		ArrayList<OsuPlay> plays = new ArrayList<OsuPlay>();
		
		JSONArray array = new JSONArray(tokener);
		array.forEach(object -> {
			JSONObject json_object = (JSONObject) object;
			
			OsuPlay play = new OsuPlay();
			
			if(!json_object.isNull("beatmap_id"))
				play.setBeatmapId(json_object.getString("beatmap_id"));
			else play.setBeatmapId(null);
			
			if(!json_object.isNull("score"))
				play.setScore(json_object.getString("score"));
			else play.setScore(null);
			
			if(!json_object.isNull("user_id"))
				play.setUserId(json_object.getString("user_id"));
			else play.setUserId(null);

			if(!json_object.isNull("maxcombo"))
				play.setMaxCombo(json_object.getString("maxcombo"));
			else play.setMaxCombo(null);

			if(!json_object.isNull("count300"))
				play.setCount300(json_object.getString("count300"));
			else play.setCount300(null);
			
			if(!json_object.isNull("count100"))
				play.setCount100(json_object.getString("count100"));
			else play.setCount100(null);
			
			if(!json_object.isNull("count50"))
				play.setCount50(json_object.getString("count50"));
			else play.setCount50(null);
			
			if(!json_object.isNull("countmiss"))
				play.setCountMiss(json_object.getString("countmiss"));
			else play.setCountMiss(null);
			
			if(!json_object.isNull("countkatu"))
				play.setCountKatu(json_object.getString("countkatu"));
			else play.setCountKatu(null);
			
			if(!json_object.isNull("countgeki"))
				play.setCountGeki(json_object.getString("countgeki"));
			else play.setCountGeki(null);
			
			if(!json_object.isNull("perfect"))
				play.setPerfect(json_object.getString("perfect"));
			else play.setPerfect(null);
			
			if(!json_object.isNull("enabled_mods"))
				play.setMods(Long.parseLong(json_object.getString("enabled_mods")));
			else play.setMods(0);
			
			if(!json_object.isNull("date"))
				play.setDate(json_object.getString("date"));
			else play.setDate(null);
			
			if(!json_object.isNull("rank"))
				play.setRank(json_object.getString("rank"));
			else play.setRank(null);
			
			if(!json_object.isNull("pp"))
				play.setPP(json_object.getString("pp"));
			else play.setPP(null);
			
			play.setMode(mode);
			
			plays.add(play);
		});
		
		return plays;
	}
	
	public static ArrayList<OsuPlay> getBestPlays(String user, int mode) {
		String url = "https://"+BASE+"get_user_best?k="+API_KEY+"&u="+user+"&m="+mode;
		return OsuAPI.playsFromJson(mode, getJSON(url));
	}
	
	public static ArrayList<OsuPlay> getRecentPlays(String user, int mode) {
		String url = "https://"+BASE+"get_user_recent?k="+API_KEY+"&u="+user+"&m="+mode;
		return OsuAPI.playsFromJson(mode, getJSON(url));
	}
	
	public static OsuUser getUser(String user, int mode) {
		String url = "https://"+BASE+"get_user?k="+API_KEY+"&u="+user+"&m="+mode;
		return OsuAPI.userFromJson(mode, getJSON(url));
	}
	
	public static ArrayList<OsuBeatmap> getBeatmapsByUser(String user, int mode) {
		String url = "https://"+BASE+"get_beatmaps?k="+API_KEY+"&u="+user+"&m="+mode;
		return OsuAPI.beatmapsFromJson(getJSON(url));
	}
	
	public static ArrayList<OsuBeatmap> getBeatmapsById(String id, int mode) {
		String url = "https://"+BASE+"get_beatmaps?k="+API_KEY+"&b="+id+"&m="+mode;
		return OsuAPI.beatmapsFromJson(getJSON(url));
	}
	
	public static ArrayList<OsuBeatmap> getBeatmapsBySetId(String set_id, int mode) {
		String url = "https://"+BASE+"get_beatmaps?k="+API_KEY+"&s="+set_id+"&m="+mode;
		return OsuAPI.beatmapsFromJson(getJSON(url));
	}
}