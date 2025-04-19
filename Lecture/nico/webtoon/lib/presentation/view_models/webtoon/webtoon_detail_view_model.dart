import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../../domain/webtoon/models/webtoon_detail_model.dart';
import '../../../domain/webtoon/models/webtoon_episode_model.dart';
import '../../../services/api_service.dart';

class WebtoonDetailViewModel extends ChangeNotifier {

  final IApiService apiService;
  final String webtoonId;

  late Future<WebtoonDetailModel> webtoon;
  late Future<List<WebtoonEpisodeModel>> episodes;
  late SharedPreferences prefs;
  bool isLiked = false;

  WebtoonDetailViewModel({
    required this.apiService,
    required this.webtoonId,
  }) {
    _initialize();
  }

  Future<void> _initialize() async {
    webtoon = apiService.getToonById(webtoonId);
    episodes = apiService.getLatestEpisodesById(webtoonId);
    await _initPrefs();
  }

  Future<void> _initPrefs() async {
    prefs = await SharedPreferences.getInstance();
    final likedToons = prefs.getStringList('likedToons');

    if (likedToons != null && likedToons.contains(webtoonId)) {
      isLiked = true;
    } else {
      await prefs.setStringList('likedToons', likedToons ?? []);
    }
    notifyListeners();
  }

  Future<void> tabLike() async {
    final likedToons = prefs.getStringList('likedToons') ?? [];

    if (isLiked) {
      likedToons.remove(webtoonId);
    } else {
      likedToons.add(webtoonId);
    }
    await prefs.setStringList('likedToons', likedToons);
    isLiked = !isLiked;
    notifyListeners();
  }
}