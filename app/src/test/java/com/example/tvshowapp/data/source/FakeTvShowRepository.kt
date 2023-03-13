package com.example.tvshowapp.data.source

import model.TVShowListRepository
import utils.TvShowListDAO

class FakeTvShowRepository(dao: TvShowListDAO) : TVShowListRepository(dao) {

}