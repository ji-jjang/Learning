import 'package:flutter/material.dart';
import 'package:webtoon/domain/webtoon/models/webtoon_model.dart';
import 'package:webtoon/services/api_service.dart';

class WebtoonListViewModel extends ChangeNotifier {
  final IApiService apiService;

  List<WebtoonModel> _webtoons = [];
  bool _isLoading = false;
  bool _hasError = false;

  WebtoonListViewModel({required this.apiService});

  List<WebtoonModel> get webtoons => _webtoons;

  bool get hasError => _hasError;

  bool get isLoading => _isLoading;

  Future<void> fetchWebtoons() async {
    _setLoading(true);
    _hasError = false;

    try {
      _webtoons = await apiService.getTodayToon();
    } catch (e) {
      _hasError = true;
    }
    _setLoading(false);
  }

  void _setLoading(bool value) {
    _isLoading = value;
    notifyListeners();
  }
}
