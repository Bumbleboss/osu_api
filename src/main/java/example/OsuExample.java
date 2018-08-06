package example;

import osuAPI.OsuAPI;

public class OsuExample {

	public static void main (String[] args) {
		OsuAPI api = new OsuAPI("your_key");

		//GETTING LEVEL OF AN OSU! USERNAME
		System.out.print(api.getUser("cookiezi", 0).getLevel());

		//GETTING TITLE OF AN OSU! BEATMAP
		System.out.print(api.getBeatmapsById("1296664", 0).get(0).getTitle());
	}
}
