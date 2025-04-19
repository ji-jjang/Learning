import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:webtoon/domain/webtoon/models/webtoon_model.dart';

import '../domain/webtoon/models/webtoon_detail_model.dart';
import '../domain/webtoon/models/webtoon_episode_model.dart';

abstract class IApiService {

  Future<List<WebtoonModel>> getTodayToon();
  Future<WebtoonDetailModel> getToonById(String id);
  Future<List<WebtoonEpisodeModel>> getLatestEpisodesById(String id);
}

class ApiServiceImpl implements IApiService {

  static const String _baseUrl = "https://webtoon-crawler.nomadcoders.workers.dev";

  @override
  Future<List<WebtoonModel>> getTodayToon() async {
    final url = Uri.parse('$_baseUrl/today');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final List<dynamic> webtoons = jsonDecode(response.body);
      return webtoons.map((json) => WebtoonModel.fromJson(json)).toList();
    } else {
      throw Exception('Failed to load today\'s webtoons');
    }
  }

  @override
  Future<WebtoonDetailModel> getToonById(String id) async {
    final url = Uri.parse("$_baseUrl/$id");
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final webtoon = jsonDecode(response.body);
      return WebtoonDetailModel.fromJson(webtoon);
    } else {
      throw Exception('Failed to load webtoon detail for id: $id');
    }
  }

  @override
  Future<List<WebtoonEpisodeModel>> getLatestEpisodesById(String id) async {
    final url = Uri.parse("$_baseUrl/$id/episodes");
    final response = await http.get(url);

    if (response.statusCode == 200) {
      final List<dynamic> episodes = jsonDecode(response.body);
      return episodes
          .map((json) => WebtoonEpisodeModel.fromJson(json))
          .toList();
    } else {
      throw Exception('Failed to load episodes for id: $id');
    }
  }
}