package com.pikaboy.basah.adapter;

//
//public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
//
//    public List<Bitmap> fileImageList;
//    public List<Uri> fileUriList;
//
//
//    public ImageListAdapter(List<Bitmap> fileImageList, List<Uri> fileUriList){
//
//        this.fileImageList = fileImageList;
//        this.fileUriList = fileUriList;
//    }
//
//    @NonNull
//    @Override
//    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ImageListAdapter.ViewHolder holder, int position) {
//
//        Bitmap uri = fileImageList.get(position);
//
//
//        holder.fileDoneView.setImageBitmap(uri);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return fileImageList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        View mView;
//
//        public ImageView fileDoneView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mView = itemView;
//
//            fileDoneView =  mView.findViewById(R.id.gambarSet);
//        }
//    }
//}
