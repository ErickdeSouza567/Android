package br.edu.up.app.data


import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ViewModelScoped
class GalleryRepository @Inject constructor() {

    suspend fun getAllPhotos(): List<String> {
        val photos = mutableListOf<String>()
        val storageReference = Firebase.storage.reference.child("gs://app2-af4b8.appspot.com")

        val result = storageReference.listAll().await()

        result.items.forEach { photoReference ->
            val downloadUrl = photoReference.downloadUrl.await()
            photos.add(downloadUrl.toString())
        }

        return photos
    }
}