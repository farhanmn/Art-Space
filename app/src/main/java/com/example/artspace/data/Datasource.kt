package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.ArtSpace

class Datasource() {
    fun loadArtSpaces(): List<ArtSpace> {
        return listOf<ArtSpace>(
            ArtSpace(R.string.art1, R.string.artist1, R.string.year1, R.drawable.__perburuan_rusa),
            ArtSpace(R.string.art2, R.string.artist2, R.string.year2, R.drawable.__badai_pasti_berlalu),
            ArtSpace(R.string.art3, R.string.artist3, R.string.year3, R.drawable.__balinese_procession)
        )
    }
}