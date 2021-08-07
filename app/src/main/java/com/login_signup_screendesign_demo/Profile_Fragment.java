package com.login_signup_screendesign_demo;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link Profile_Fragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link Profile_Fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Profile_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static TextView tagline,Name,no1,no2,no3,des1,des2,des3;

    LinearLayout ques_tab,ans_tab;

    final String TAG = "Image";

    final String ROOT_URL = "http://192.168.43.1/sj/";

    User_Info info;

    private CircleImageView imageView;

    ImageView iview;
    CameraPhoto cameraPhoto;

    final int IMG_REQUEST = 1990;
    String url;

    Bitmap bitmap;

    ImageView b1,b2,b3;
    private static Bitmap bmap;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public Profile_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Profile_Fragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static Profile_Fragment newInstance(String param1, String param2) {
//        Profile_Fragment fragment = new Profile_Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        View v = inflater.inflate(R.layout.fragment_profile_, container, false);
        Intent i = getActivity().getIntent();
        info = (User_Info) i.getSerializableExtra("Com_object");
        if (info != null)
            Log.d("sab", "inside profile" + info.getQues_asked());

        tagline = (TextView) v.findViewById(R.id.tag);
        Name = (TextView) v.findViewById(R.id.name);
        imageView = (CircleImageView) v.findViewById(R.id.logo);
        iview = (ImageView) v.findViewById(R.id.toggle);
        no1 = (TextView) v.findViewById(R.id.no1);
        no2 = (TextView) v.findViewById(R.id.no2);
        no3 = (TextView) v.findViewById(R.id.no3);
        des1 = (TextView) v.findViewById(R.id.desc1);
        des2 = (TextView) v.findViewById(R.id.desc2);
        des3 = (TextView) v.findViewById(R.id.desc3);

        des1.setText("Infosys,GM");
        des2.setText("PICT,BE");
        des3.setText("Pune");

        ques_tab = (LinearLayout) v.findViewById(R.id.ques_tab);
        ans_tab = (LinearLayout) v.findViewById(R.id.all_answers);

        tagline.setText(info.getTagLine());
        Name.setText(info.getName());
        no1.setText(""+info.getQues_asked());
        no2.setText(""+info.getAns_given());
        no3.setText(""+info.getFollower());
        b1=(ImageView)v.findViewById(R.id.btn1);
        b2=(ImageView)v.findViewById(R.id.btn2);
        b3=(ImageView)v.findViewById(R.id.btn3);




        ques_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionProfile questionProfile = new QuestionProfile();
                Bundle bundle = new Bundle();
//                bundle.putString("NAME",info.getName());
//                bundle.putString("TAGLINE",info.getTagLine());
//                bundle.putInt("USERID",info.getUser_id());
                bundle.putSerializable("Com_object",info);
                bundle.putString("IMAGE",url);
                questionProfile.setArguments(bundle);
                FragmentManager manager=getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.content,questionProfile).commit();

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(),"button 1",Toast.LENGTH_SHORT).show();
                //  bundle.putSerializable("Com_object",info);
                Intent intent1=new Intent(Profile_Fragment.this.getActivity(),employment_cred.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key",info.getUser_id());
                intent1.putExtras(bundle); //Put your id to your next Intent
                startActivity(intent1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(),"button 2",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                //   bundle.putSerializable("Com_object",info);
                Intent intent1=new Intent(Profile_Fragment.this.getActivity(),education_cred.class);
                bundle.putInt("key",info.getUser_id());
                intent1.putExtras(bundle); //Put your id to your next Intent
                // intent1.putExtra("info",bundle);
                startActivity(intent1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(),"button 3",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                //  bundle.putSerializable("Com_object",info);
                Intent intent1=new Intent(Profile_Fragment.this.getActivity(),location_cred.class);
                bundle.putInt("key",info.getUser_id());
                intent1.putExtras(bundle); //Put your id to your next Intent
                //  intent1.putExtra("info",bundle);
                startActivity(intent1);
            }
        });



        ans_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




//        iview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu dropDownMenu = new PopupMenu(Profile_Fragment.this.getActivity(), iview);
//                dropDownMenu.getMenuInflater().inflate(R.menu.menu_drop_down, dropDownMenu.getMenu());
//                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//
//                        switch (menuItem.getItemId()){
//                            case R.id.dropdown_menu1:
//                                onDestroy();
//
//                                FragmentManager fragmentManager = null;
//                                fragmentManager
//                                        .beginTransaction()
//                                        .replace(R.id.frameContainer, new Login_Fragment(),
//                                                Utils.Login_Fragment).commit();
//                                return true;
//                        }
//
////                        Toast.makeText(Profile_Fragment.this.getActivity(), "You have clicked " + menuItem.getTitle(), Toast.LENGTH_LONG).show();
//                        return true;
//                    }
//                });
//                dropDownMenu.show();
//            }
//
//        });

//        Bitmap bitmap=null;
//        try {
//            bitmap = ImageLoader.init().from(info.getImageURL()).requestSize(80,80).getBitmap();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        imageView.setImageBitmap(bitmap);

//        if(info.getImageURL().equals("https://wwwqueriuscom.000webhostapp.com/index.jpg")){
//            Picasso.with(Profile_Fragment.this.getActivity()).load(info.getImageURL()).into(imageView);
//        }
//        else {
////            final ProviderInfo info = getContext().getPackageManager()
////                    .resolveContentProvider(authority, PackageManager.GET_META_DATA);
////            final XmlResourceParser in = info.loadXmlMetaData( //560
////                    getContext().getPackageManager(), META_DATA_FILE_PROVIDER_PATHS);
////            Bitmap btmp = Bitmap.createBitmap(drawable.getBitmap());
//            Picasso.with(Profile_Fragment.this.getActivity()).load(info.getImageURL()).into(imageView);
//
//
//
////            Uri uri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".fileprovider",file);
////            Log.d(TAG, "" + uri);
////            imageView.setImageURI(uri);
////            imageView.setImageBitmap(BitmapFactory.decodeFile(info.getImageURL()));
//        }
//        imageView.setImageBitmap(BitmapFactory.decodeFile());

//        cameraPhoto = new CameraPhoto(Profile_Fragment.this.getContext());


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            Log.d(TAG,"on click");
//                            select_image();
                        checkImagePermissions();


                    }
                });

//       bmap = SaveSharedPreference.getPrefImageUrl(getActivity().getApplicationContext());
//        if(bmap!=null){
////            imageView.setImageBitmap(bmap);
//            Glide.with(getActivity().getApplicationContext()).load(bitmapToByte(bmap)).asBitmap().into(imageView);
//        }
//
//        if(SaveSharedPreference.getPrefImageUrl(getActivity().getApplicationContext())==null){
//            fetchImage();
//        }
//        else {
//            bmap = SaveSharedPreference.getPrefImageUrl(getActivity().getApplicationContext());
//            Glide.with(getActivity().getApplicationContext()).load(bitmapToByte(bmap)).asBitmap().into(imageView);
//        }

        fetchImage();
        getCred();
        return v;
        }



        private void select_image(){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,IMG_REQUEST);
        }


    private void fetchImage(){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        FetchImageAPI api = retrofit.create(FetchImageAPI.class);

        Call<List<ImageModel>> call = api.fetchImage(info.getUser_id());
        call.enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                if(response.isSuccessful()){
                    List<ImageModel> list = response.body();
                    ImageModel model = list.get(0);
                     url = model.getPath();
                    Log.d("url","the url is :"+url);
                    String full_url = ROOT_URL+url;
//                    byte[] decode_string = Base64.decode(full_url,Base64.DEFAULT);
//                    Bitmap decoded_image = BitmapFactory.decodeByteArray(decode_string, 0, decode_string.length);
                    Picasso.with(getActivity().getApplicationContext()).load(full_url).into(imageView);
//                    imageView.setImageBitmap(decoded_image);
                }
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){

            Uri path = data.getData();


            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
                SaveSharedPreference.setPrefImageUrl(getActivity().getApplicationContext(),bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    //Converts the selected Bitmap into a string value
    private String imageToString(){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte imgByte[] = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    private void uploadImage(){

        String Image = imageToString();
        String Title = info.getName();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ImageInterface imageInterface = retrofit.create(ImageInterface.class);

        Call<Integer> call = imageInterface.uploadImage(Title,Image,info.getUser_id());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int value=-1;
                value=response.body();

                if(value==1){
                    Toast.makeText(Profile_Fragment.this.getActivity(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Profile_Fragment.this.getActivity(),"Image Not Uploaded",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(Profile_Fragment.this.getActivity(),"Check Your Network",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getCred() {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
        allcred allcred1 = retrofit.create(allcred.class);


        final int user_id = info.getUser_id();
        Log.d("hello","inisdde getCred"+user_id);
        //
        Call<Integer> call = allcred1.ispresent(user_id);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (response.body() == 1) {
                    int uid = user_id;

                    allcredAPI2 allcred2 = retrofit.create(allcredAPI2.class);
                    // Toast.makeText(getApplicationContext(), "already present", Toast.LENGTH_SHORT).show();
                    Call<List<getAllCred>> call1 = allcred2.get_allcred(uid);
                    call1.enqueue(new Callback<List<getAllCred>>() {
                        @Override
                        public void onResponse(Call<List<getAllCred>> call, Response<List<getAllCred>> response) {
                            if (response.isSuccessful()) {
                                // Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                                List<getAllCred> list = response.body();
                                getAllCred all = list.get(0);

                                des1.setText(all.getCompany() + "," + all.getPosition());
                                des2.setText(all.getSchool() + "," + all.getBranch());
                                des3.setText(all.getLocation());


                            } else {
                                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<List<getAllCred>> call, Throwable t) {
                            Toast.makeText(getContext(), "Check Your networkinisde", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "getting id " + t.getMessage());
                            t.printStackTrace();
                        }
                    });

                } else {
                    Toast.makeText(getContext(), "add cred", Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
//                Toast.makeText(getContext(), "Check Your network", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "getting id " + t.getMessage());
                t.printStackTrace();
            }
        });

    }



    //    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


    private void checkImagePermissions(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(Profile_Fragment.this.getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED   && ActivityCompat.checkSelfPermission(Profile_Fragment.this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG,"Inside checkImage");
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
                return;
            }
            else{
                Log.d(TAG,"Inside checkImage else part");
//                try {
////                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
//                    select_image();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                select_image();
//                cameraPhoto.addToGallery();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG,"Inside switch case");
//                        try {
//                            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        cameraPhoto.addToGallery();
                        select_image();
                    }
                }else{
                    Toast.makeText(Profile_Fragment.this.getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkImagePermissions();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }



    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if(resultCode == RESULT_OK){
//                if(requestCode==CAMERA_REQUEST){
////                    Uri uri = data.getData();
////                    String filePath = getRealPathFromURIPath(uri, MainActivity.this);
//                    String photoPath = cameraPhoto.getPhotoPath();
//                    Log.d(TAG,"Inside onActivityforresult"+photoPath);
//                    try {
//                        Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(80,80).getBitmap();
//                        imageView.setImageBitmap(bitmap);
//                        File file = new File(photoPath);
//                        Uri uri = Uri.fromFile(file);
//                        String filepath = getRealPathFromURIPath(uri,Profile_Fragment.this.getActivity());
//                        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
//                        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("uploaded_file", file.getName(), mFile);
//                        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
//                       uploadImage(fileToUpload,filename);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                        Toast.makeText(Profile_Fragment.this.getActivity(),"Something Went Wrong Again",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//    }
//
//
//    public void uploadImage(MultipartBody.Part fileToUpload,RequestBody filename){
//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        UploadImageInterface imageInterface = retrofit.create(UploadImageInterface.class);
//
//        Call<UploadObject> call = imageInterface.uploadFile(fileToUpload,filename);
//        call.enqueue(new Callback<UploadObject>() {
//            @Override
//            public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
//                Toast.makeText(Profile_Fragment.this.getActivity(), "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
//                Toast.makeText(Profile_Fragment.this.getActivity(), "Success " + response.body().getSuccess(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<UploadObject> call, Throwable t) {
//                Log.d(TAG, "Error " + t.getMessage());
//            }
//        });
//    }
//
//
//    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
//        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) {
//            return contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            return cursor.getString(idx);
//        }
//    }





//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        return false;
//    }
}
