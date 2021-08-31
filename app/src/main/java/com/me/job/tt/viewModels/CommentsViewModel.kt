package com.me.job.tt.viewModels

import androidx.lifecycle.MutableLiveData
import com.me.job.tt.data.remote.request.CommentRequest
import com.me.job.tt.data.remote.response.CommentResponse

//csh
/*import com.android.tintoung.data.remote.request.CommentDeleteRequest
import com.android.tintoung.data.remote.request.CommentRequest
import com.android.tintoung.data.remote.request.EditCommentRequest
import com.android.tintoung.data.remote.response.*
import com.android.tintoung.models.AmhantayarModel*/

class CommentsViewModel : BaseViewModel() {

    //val mResponseGetCommentsLD: MutableLiveData<GetCommentsResponse>
    val mResponseCommentLD: MutableLiveData<CommentResponse>
    /*val mResponseAllCommentLD: MutableLiveData<AllCommentResponse>
    val mResponseChildCommentLD: MutableLiveData<ChildCommentResponse>
    val mResponseDeleteCommentLD: MutableLiveData<CommentDeleteResponse>
    val mResponseEditCommentLD: MutableLiveData<EditCommentResponse>*/

    init {
        super.initViewModel()
        //mResponseGetCommentsLD = MutableLiveData()
        mResponseCommentLD = MutableLiveData()
       // mResponseDeleteCommentLD = MutableLiveData()
       // mResponseAllCommentLD = MutableLiveData()
        //mResponseEditCommentLD = MutableLiveData()
       // mResponseChildCommentLD = MutableLiveData()
    }

   /* fun getComments(postId: String, userId: String) {
        mCompositeDisposable.add(
            AmhantayarModel.getInstance().getComments(
                postId,
                userId,
                mResponseGetCommentsLD,
                mErrorLD
            )
        )
    }

    // comment & reply comment
    fun getAllComment(
        postId: String,
        userId: String,
        parentCommentId: String?,
        page: Int,
        count: Int
    ) {
        mCompositeDisposable.add(
            AmhantayarModel.getInstance().getAllComment(
                postId,
                userId,
                parentCommentId,
                page,
                count,
                mResponseAllCommentLD,
                mErrorLD
            )
        )
    }

    fun getChildComment(
        postId: String,
        userId: String,
        parentCommentId: String?,
        page: Int,
        count: Int
    ) {
        mCompositeDisposable.add(
            AmhantayarModel.getInstance().getChildComment(
                postId,
                userId,
                parentCommentId,
                page,
                count,
                mResponseChildCommentLD,
                mErrorLD
            )
        )
    }

    fun comment(postId: String, userId: String, request: CommentRequest) {
        mCompositeDisposable.add(
            AmhantayarModel.getInstance().comment(
                postId,
                userId,
                request,
                mResponseCommentLD,
                mErrorLD
            )
        )
    }*/

    /*fun deleteComment(request: CommentDeleteRequest) {
        mCompositeDisposable.add(
            AmhantayarModel.getInstance().deleteComment(
                request,
                mResponseDeleteCommentLD,
                mErrorLD
            )
        )
    }*/

    /*fun updateComment(commentId: String, feedId: String, request: EditCommentRequest) {
        mCompositeDisposable.add(
            AmhantayarModel.getInstance().updateComment(
                commentId,
                feedId,
                request,
                mResponseEditCommentLD,
                mErrorLD
            )
        )
    }*/

}