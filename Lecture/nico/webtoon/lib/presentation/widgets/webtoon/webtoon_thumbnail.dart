import 'package:flutter/material.dart';

class WebtoonThumbnail extends StatelessWidget {
  final String id;
  final String thumb;

  const WebtoonThumbnail({super.key, required this.id, required this.thumb});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
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
                  blurRadius: 25,
                  offset: Offset(10, 10),
                  color: Colors.black.withAlpha(10),
                ),
              ],
            ),
            child: Image.network(thumb),
          ),
        ),
      ],
    );
  }
}
