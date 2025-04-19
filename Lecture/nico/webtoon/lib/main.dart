import 'dart:io';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:webtoon/presentation/pages/webtoon/webtoon_list_screen.dart';
import 'package:webtoon/services/api_service.dart';
import 'package:webtoon/presentation/view_models/webtoon/webtoon_list_view_model.dart';

class MyHttpOverrides extends HttpOverrides {
  @override
  HttpClient createHttpClient(SecurityContext? context) {
    return super.createHttpClient(context)
      ..userAgent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36';
  }
}
void main() {
  HttpOverrides.global = MyHttpOverrides();

  runApp(
    MultiProvider(
      providers: [
        Provider<IApiService>(create: (_) => ApiServiceImpl()),
        ChangeNotifierProvider<WebtoonListViewModel>(
          create: (context) => WebtoonListViewModel(
            apiService: context.read<IApiService>(),
          )..fetchWebtoons(),
        )
      ],
      child: const App(),
    ),
  );
}

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: WebtoonListScreen()
    );
  }
}

