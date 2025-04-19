import 'package:flutter/material.dart';
import 'package:shimmer/shimmer.dart';
import 'webtoon.dart';
import '../../view_models/webtoon/webtoon_list_view_model.dart';

class WebtoonList extends StatelessWidget {
  final WebtoonListViewModel viewModel;

  const WebtoonList({required this.viewModel});

  @override
  Widget build(BuildContext context) {
    return ListView.separated(
      scrollDirection: Axis.horizontal,
      itemCount: viewModel.webtoons.length,
      padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 20),
      itemBuilder: (context, index) {
        final webtoon = viewModel.webtoons[index];
        return Webtoon(
          title: webtoon.title,
          thumb: webtoon.thumb,
          id: webtoon.id,
        );
      },
      separatorBuilder: (context, index) => const SizedBox(width: 40),
    );
  }
}

class WebtoonSkeletonList extends StatelessWidget {
  const WebtoonSkeletonList();

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const SizedBox(height: 50),
        Expanded(
          child: ListView.separated(
            scrollDirection: Axis.horizontal,
            padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 20),
            itemCount: 3,
            itemBuilder: (_, __) => const _WebtoonSkeletonItem(),
            separatorBuilder: (_, __) => const SizedBox(width: 40),
          ),
        ),
      ],
    );
  }
}

class _WebtoonSkeletonItem extends StatelessWidget {
  const _WebtoonSkeletonItem({super.key});

  @override
  Widget build(BuildContext context) {
    return Shimmer.fromColors(
      baseColor: Colors.grey.shade300,
      highlightColor: Colors.grey.shade100,
      child: Column(
        children: [
          Container(
            width: 250,
            height: 320,
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(15),
              boxShadow: [
                BoxShadow(
                  blurRadius: 15,
                  offset: const Offset(4, 4),
                  color: Colors.black.withValues(alpha: 0.1),
                )
              ],
            ),
          ),
          const SizedBox(height: 10),
          Container(
            width: 120,
            height: 16,
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(8),
            ),
          )
        ],
      ),
    );
  }
}
