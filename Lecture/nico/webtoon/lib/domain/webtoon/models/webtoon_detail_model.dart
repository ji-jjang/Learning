import 'package:json_annotation/json_annotation.dart';

part 'webtoon_detail_model.g.dart';

@JsonSerializable()
class WebtoonDetailModel {
  final String title;
  final String about;
  final String genre;
  final String age;

  const WebtoonDetailModel({
    required this.title,
    required this.about,
    required this.genre,
    required this.age,
  });

  factory WebtoonDetailModel.fromJson(Map<String, dynamic> json) =>
      _$WebtoonDetailModelFromJson(json);

  Map<String, dynamic> toJson() => _$WebtoonDetailModelToJson(this);
}
