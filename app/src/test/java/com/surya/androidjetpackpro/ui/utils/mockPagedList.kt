package com.surya.androidjetpackpro.ui.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.stubbing.Answer

/**
 * Created by suryamudti on 25/09/2019.
 */

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList: PagedList<*> = Mockito.mock(PagedList::class.java)

    val answer: Answer<T> = Answer { invocation ->
        val index = invocation.arguments[0] as Int
        list[index]
    }

    Mockito.`when`<T>(pagedList[ArgumentMatchers.anyInt()] as T).thenAnswer(answer)
    Mockito.`when`(pagedList.size).thenReturn(list.size)
    return pagedList as PagedList<T>
}