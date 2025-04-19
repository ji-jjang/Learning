import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:webtoon/domain/webtoon/models/webtoon_model.dart';
import 'package:webtoon/presentation/view_models/webtoon/webtoon_list_view_model.dart';
import 'package:webtoon/services/api_service.dart';

import 'webtoon_view_model_test.mocks.dart';

@GenerateMocks([IApiService])
void main() {
  group('WebtoonViewModel', () {

    late WebtoonListViewModel viewModel;
    late MockIApiService mockIApiService;

    setUp(() {
      mockIApiService = MockIApiService();
      viewModel = WebtoonListViewModel(apiService: mockIApiService);
    });

    test('초기 상태는 올바르다', () {
      expect(viewModel.isLoading, false);
      expect(viewModel.hasError, false);
      expect(viewModel.webtoons, isEmpty);
    });

    test('fetchWebtoons 성공 시 데이터가 로드된다', () async {
      // arrange
      final mockWebtoons = [
        WebtoonModel(id: '1', title: 'Test Toon', thumb: 'https://test.com')
      ];

      when(mockIApiService.getTodayToon()).thenAnswer((_) async => mockWebtoons);

      // act
      await viewModel.fetchWebtoons();

      // assert
      expect(viewModel.isLoading, false);
      expect(viewModel.hasError, false);
      expect(viewModel.webtoons, mockWebtoons);
    });

    test('로딩 중 상태가 true로 시작했다가 false로 바뀐다', () async {
      final mockWebtoons = [
        WebtoonModel(id: '1', title: 'Test Toon', thumb: 'https://test.com'),
      ];

      when(mockIApiService.getTodayToon()).thenAnswer(
            (_) async {
          return mockWebtoons;
        },
      );

      final log = <bool>[];

      // viewModel.addListener(() {
      //   log.add(viewModel.isLoading);
      // });

      await viewModel.fetchWebtoons();

      expect(log, containsAllInOrder([true, false]));
      expect(viewModel.isLoading, false);
      expect(viewModel.hasError, false);
      expect(viewModel.webtoons, mockWebtoons);
    });

    test('fetchWebtoons 실패 시 hasError가 true가 된다', () async {
      // arrange
      when(mockIApiService.getTodayToon()).thenThrow(Exception('API 실패'));

      // act
      await viewModel.fetchWebtoons();

      // assert
      expect(viewModel.isLoading, false);
      expect(viewModel.hasError, true);
      expect(viewModel.webtoons, isEmpty);
    });
  });
}