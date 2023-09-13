package com.yusufmendes.sisterslabakademikotlinodevlerihafta8.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.model.Content
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.repository.HomeRepository

class ListViewModel : ViewModel() {


    private val homeRepository = HomeRepository()
    var contentLiveData: MutableLiveData<List<Content>> = MutableLiveData()

    fun getContent(refContent: DatabaseReference) =
        homeRepository.getContentFromFirebase(refContent, contentLiveData)
}