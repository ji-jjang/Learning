import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:webtoon/presentation/widgets/webtoon/webtoon_list.dart';
import '../../view_models/webtoon/webtoon_list_view_model.dart';

class WebtoonListScreen extends StatelessWidget {
  const WebtoonListScreen({super.key});

  @override
  Widget build(BuildContext context) {

    final webtoonViewModel = context.watch<WebtoonListViewModel>();

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 2,
        backgroundColor: Colors.white,
        foregroundColor: Colors.green,
        title: const Text('오늘의 웹툰', style: TextStyle(fontSize: 24)),
      ),
      body: webtoonViewModel.isLoading ? WebtoonSkeletonList() : Column(
        children: [
          const SizedBox(height: 50),
          Expanded(child: WebtoonList(viewModel: webtoonViewModel)),
        ],
      )
    );
  }
}
