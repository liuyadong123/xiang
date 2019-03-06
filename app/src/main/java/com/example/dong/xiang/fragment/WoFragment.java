package com.example.dong.xiang.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.xiang.R;
import com.example.dong.xiang.activity.CircleActivity;
import com.example.dong.xiang.activity.DatumActivity;
import com.example.dong.xiang.activity.DibuActivity;
import com.example.dong.xiang.activity.SiteActivity;
import com.example.dong.xiang.activity.TrackActivity;
import com.example.dong.xiang.activity.WalletActivity;
import com.example.dong.xiang.activity.landingActivity;
import com.example.dong.xiang.bean.MingBean;
import com.example.dong.xiang.bean.ShangBean;
import com.example.dong.xiang.bean.ZiliaoBean;
import com.example.dong.xiang.bean.ZuJiBean;
import com.example.dong.xiang.contract.ZixiaoContract;
import com.example.dong.xiang.presenter.ZiLiaoPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class WoFragment extends Fragment implements ZixiaoContract.Contract.ZiView {
  private TextView circle,datum,site,track,wallet,name,tuichu;
    private ZiliaoBean.Result result;
    private SimpleDraweeView touxing;
    private ZiLiaoPresenter ziLiaoPresenter1;
    private static final String TAG="photoactivity";
    private static final int CROP_SMALL_PICTURE = 0;
    private static final int TAKE_PICTURE = 1;
    private static final int CHOOSE_PICTURE = 2;
    private Bitmap mBitmap;
    Uri tmpUri;
    private String path;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.activity_wo,container,false);
         initView(view);
        return view;
    }

    private void initView(View view) {
        circle=view.findViewById(R.id.circle);
        datum=view.findViewById(R.id.datum);
        site=view.findViewById(R.id.site);
        track=view.findViewById(R.id.track);
        wallet=view.findViewById(R.id.wallet);
        name=view.findViewById(R.id.txt_username_wdf);
        tuichu=view.findViewById(R.id.txt_tuichu);
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),landingActivity.class));
            }
        });
        ziLiaoPresenter1 = new ZiLiaoPresenter(this);
        ziLiaoPresenter1.ZiLiao(new HashMap<String, String>());

        touxing=view.findViewById(R.id.touxiang);
        touxing.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//挂载状态
                    path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Pictures/hi.jpg";
                    System.out.println("path:====="+ path);
                    File file = new File(path);
                    //如果文件存在
                    if (file!=null&&file.exists()){
                        //图片请求体
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                        //对图片请求体对象，封装成multipart对象，文件表单对象
                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image",file.getName(),requestBody);
                        //P层进行网络请求

                        HashMap<String,String> params =new HashMap<>();
                        params.put("image",filePart+"");
                        ziLiaoPresenter1.shang(params);
                    }else{
                        Toast.makeText(getActivity(), "请选择文件",Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("添加图片");
                        String [] items={"从相册选择照片","拍照"

                        };
                        builder.setNegativeButton("取消", null);
                        builder.setItems(items,new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                switch (which) {
                                    case CHOOSE_PICTURE:
                                        Intent openIntent=new Intent(Intent.ACTION_GET_CONTENT);
                                        openIntent.addCategory(Intent.CATEGORY_OPENABLE);
                                        openIntent.setType("image/*");
                                        //用startActivityForResult方法，重写onActivityResult()方法，拿到图片进行裁剪操作
                                        startActivityForResult(openIntent, CHOOSE_PICTURE);

                                        break;
                                    case TAKE_PICTURE:
                                        Intent openCamreaIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        tmpUri=Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"test_image.jpg"));
                                       openCamreaIntent.putExtra(MediaStore.EXTRA_OUTPUT, tmpUri);
                                    //    openCamreaIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                        // 将拍照所得的相片保存到SD卡根目录openCamreaIntent.putExtra(MediaStore.EXTRA_OUTPUT, tmpUri);
                                        startActivityForResult(openCamreaIntent, TAKE_PICTURE);
                                        break;

                                    default:
                                        break;
                                }
                            }
                        });

                        builder.show();

                    }
                }
            }
        });




            //个人信息
        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DatumActivity.class));
            }
        });
        //我的圈子
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CircleActivity.class));
            }
        });
        //我的足迹
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TrackActivity.class));
            }
        });
        //我的钱包
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),WalletActivity.class));
            }
        });
        //我的收获地址
        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SiteActivity.class));
            }
        });
    }


    @Override
    public void ZiSuccess(ZiliaoBean ziliaoBean) {
        name.setText(ziliaoBean.result.nickName);
        Uri uri=Uri.parse(ziliaoBean.result.headPic);
        DraweeController draweeController=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        RoundingParams roundingParams =RoundingParams.fromCornersRadius(50);
        touxing.getHierarchy().setRoundingParams(roundingParams);
        touxing.setController(draweeController);

    }

    @Override
    public void MingSuccess(MingBean mingBean) {

    }

    @Override
    public void ZujiSuccess(ZuJiBean zuJiBean) {

    }

    @Override
    public void shangSuccess(ShangBean shangBean) {
      Toast.makeText(getActivity(),shangBean.message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failure(String msg) {

    }
    protected void cutImage(Uri uri){
        if(uri==null){
            Log.i(TAG, "this is not exist");
        }


        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", true);
        //设置aspectX y设置宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //outputX 剪裁图片的宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);

    }
    protected void setImageToView(Intent data){
        Bundle extras=data.getExtras();
        if(extras!=null){
            mBitmap=extras.getParcelable("data");
            touxing.setImageBitmap(mBitmap);

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    // 对图片进行裁剪处理
                    cutImage(tmpUri);
                    break;
                case CHOOSE_PICTURE:
                    // 对图片进行裁剪处理返回
                    cutImage(data.getData());

                    break;
                case CROP_SMALL_PICTURE:
                    //将裁剪后的图片上传并设置到到ImageView控件中
                    if (data != null) {
                        setImageToView(data);
                    }

                    break;


                default:
                    break;
            }
        }
    }


}
