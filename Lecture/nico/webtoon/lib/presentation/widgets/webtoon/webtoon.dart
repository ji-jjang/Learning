import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:webtoon/presentation/pages/webtoon/webtoon_detail_screen.dart';
import 'package:webtoon/services/api_service.dart';

class Webtoon extends StatelessWidget {
  final String title, thumb, id;

  const Webtoon({
    super.key,
    required this.title,
    required this.thumb,
    required this.id,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder:
                (context) => DetailScreen(
                  title: title,
                  thumb: thumb,
                  id: id,
                  apiService: context.read<IApiService>(),
                ),
            fullscreenDialog: true,
          ),
        );
      },
      child: Column(
        children: [
          Hero(
            tag: id,
            child: Container(
              width: 250,
              clipBehavior: Clip.hardEdge,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(15),
                boxShadow: [
                  BoxShadow(
                    blurRadius: 15,
                    offset: Offset(10, 10),
                    color: Colors.black.withValues(alpha: 1),
                  ),
                ],
              ),
              child: Image.network(thumb),
            ),
          ),
          SizedBox(height: 10),
          Text(title, style: TextStyle(fontSize: 22)),
        ],
      ),
    );
  }
}
