<img align="right" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Osu%21Logo_%282015%29.png/600px-Osu%21Logo_%282015%29.png" height="100" width="100">

## Osu! API
A Java API wrapper for [Osu!](https://osu.ppy.sh/home)

## How to use
For setting the API key
```Java
OsuAPI api = new OsuAPI("your_key");
```
Requesting an Osu! user level
```Java
System.out.print(api.getUser("cookiezi", 0).getLevel());
```
Requesting an Osu! beatmap title
```Java
System.out.print(api.getBeatmapsById("1296664", 0).get(0).getTitle());
```

## Download
In a min

## Dependencies
* [json-20180130](https://github.com/stleary/JSON-java)
* [okhttp-3.10.0](https://github.com/square/okhttp)
* [okio-1.14.1](https://github.com/square/okio/)

## Info 
Originally made by [21Joakim](https://github.com/21Joakim/)