import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:webtoon/presentation/view_models/webtoon/webtoon_detail_view_model.dart';
import 'package:webtoon/presentation/widgets/webtoon/webtoon_summary_text.dart';
import 'package:webtoon/presentation/widgets/webtoon/webtoon_thumbnail.dart';
import '../../../domain/webtoon/models/webtoon_detail_model.dart';
import '../../../domain/webtoon/models/webtoon_episode_model.dart';
import '../../../services/api_service.dart';
import '../../widgets/webtoon/episode.dart';

class DetailScreen extends StatelessWidget {
  final String title, thumb, id;
  final IApiService apiService;

  const DetailScreen({
    super.key,
    required this.title,
    required this.thumb,
    required this.id,
    required this.apiService,
  });

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create:
          (_) => WebtoonDetailViewModel(apiService: apiService, webtoonId: id),
      child: Consumer<WebtoonDetailViewModel>(
        builder: (context, viewModel, child) {
          return Scaffold(
            backgroundColor: Colors.white,
            appBar: AppBar(
              elevation: 2,
              backgroundColor: Colors.white,
              foregroundColor: Colors.green,
              actions: [
                IconButton(
                  onPressed: viewModel.tabLike,
                  icon: Icon(
                    viewModel.isLiked
                        ? Icons.favorite
                        : Icons.favorite_outline_outlined,
                  ),
                ),
              ],
              title: Text(title, style: const TextStyle(fontSize: 24)),
            ),
            body: SingleChildScrollView(
              child: Padding(
                padding: const EdgeInsets.all(50),
                child: Column(
                  children: [
                    WebtoonThumbnail(id: id, thumb: thumb),
                    const SizedBox(height: 25),
                    FutureBuilder<WebtoonDetailModel>(
                      future: viewModel.webtoon,
                      builder: (context, snapshot) {
                        if (snapshot.hasData) {
                          return WebtoonSummaryText(detail: snapshot.data!);
                        }
                        return const CircularProgressIndicator();
                      },
                    ),
                    const SizedBox(height: 25),
                    FutureBuilder<List<WebtoonEpisodeModel>>(
                      future: viewModel.episodes,
                      builder: (context, snapshot) {
                        if (snapshot.hasData) {
                          return Column(
                            children: [
                              for (var episode in snapshot.data!)
                                Episode(episode: episode, webtoonId: id),
                            ],
                          );
                        }
                        return const CircularProgressIndicator();
                      },
                    ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
    );
  }
}
