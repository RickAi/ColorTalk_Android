package navyblue.top.colortalk.rest.services;


import navyblue.top.colortalk.mvp.models.Comment;
import navyblue.top.colortalk.mvp.models.CommentLike;
import navyblue.top.colortalk.mvp.models.Moment;
import navyblue.top.colortalk.mvp.models.MomentLike;
import navyblue.top.colortalk.mvp.models.RongToken;
import navyblue.top.colortalk.mvp.models.User;
import navyblue.top.colortalk.rest.models.CommentResponse;
import navyblue.top.colortalk.rest.models.ImageResponse;
import navyblue.top.colortalk.rest.models.MomentResponse;
import navyblue.top.colortalk.rest.models.UserInfo;
import navyblue.top.colortalk.rest.models.UserResponse;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by CIR on 16/1/16.
 */
public interface ColorTalkService {
    /**
     * Moment
     */
    @FormUrlEncoded
    @POST("getMoments")
    Observable<MomentResponse> getMoments(@Query("page") int page, @Field("user_id") String user_id);

    // ['user_id', 'image_name', 'text']
    @FormUrlEncoded
    @POST("moments")
    Observable<Moment> createMoment(@Field("user_id") String user_id, @Field("image_name") String image_name,
                                    @Field("text") String text);

    // ['user_id']
    @FormUrlEncoded
    @POST("moments/{moments}/like")
    Observable<MomentLike> likeMoment(@Path("moments") int moment_id, @Field("user_id") String user_id);

    /**
     * Comment
     */
    @FormUrlEncoded
    @POST("moments/{moments}/getMoments")
    Observable<CommentResponse> getComments(@Path("moments") int moment_id, @Query("page") int page, @Field("user_id") String user_id);

    // ['user_id', 'text']
    @FormUrlEncoded
    @POST("moments/{moments}/comments")
    Observable<Comment> createComment(@Path("moments") int moment_id, @Field("user_id") String user_id, @Field("text") String text);

    // ['user_id']
    @FormUrlEncoded
    @POST("moments/{moments}/comments/{comments}/like")
    Observable<CommentLike> likeComment(@Path("moments") int moment_id, @Path("comments") int comment_id,
                                       @Field("user_id") String user_id);

    @GET("users/{users}/user_info")
    Observable<UserInfo> getUserInfo(@Path("users") int user_id);
    @GET("images")
    Observable<ImageResponse> getImages();
    @GET("users")
    Observable<UserResponse> getUsers();

    // ['user_id']
    @FormUrlEncoded
    @POST("images/private")
    Observable<ImageResponse> getPrivateImages(@Field("user_id") String user_id);

//    // ['name', 'email', 'password', 'is_third', 'gender', 'birthday'];
//    @FormUrlEncoded
//    @POST("register")
//    Observable<User> createUser(@Field("name") String name, @Field("email") String email,
//                                @Field("password") String password, @Field("is_third") String is_third,
//                                @Field("gender") String gender, @Field("birthday") String birthday);

    // local ['email', 'password', 'is_third']
    @FormUrlEncoded
    @POST("login")
    Observable<User> login( @Field("email") String email, @Field("password") String password,
                            @Field("is_third") int is_third);

    // third ['uid', 'is_third']
    @FormUrlEncoded
    @POST("login")
    Observable<User> login( @Field("uid") String uid, @Field("is_third") int is_third);

    //  ['email', 'password']
    @FormUrlEncoded
    @POST("register")
    Observable<User> register( @Field("email") String email, @Field("password") String password);

    //  ['email']
    @FormUrlEncoded
    @POST("forget")
    Observable<User> forget( @Field("email") String email);

    @FormUrlEncoded
    @POST("token/rong")
    Observable<RongToken> getRongToken(@Field("user_id") String user_id, @Field("name") String name,
                                    @Field("url") String url);

}
