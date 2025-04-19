import 'package:flutter/cupertino.dart';
import 'package:webtoon/domain/webtoon/models/webtoon_detail_model.dart';

class WebtoonSummaryText extends StatelessWidget {
  final WebtoonDetailModel detail;

  const WebtoonSummaryText({super.key, required this.detail});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          detail.about,
          style: const TextStyle(fontSize: 16),
        ),
        const SizedBox(height: 15),
        Text(
          '${detail.genre}  ${detail.age}',
          style: const TextStyle(fontSize: 16),
        ),
      ],
    );
  }
}
