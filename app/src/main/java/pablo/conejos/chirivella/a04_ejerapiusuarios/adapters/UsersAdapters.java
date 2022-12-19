package pablo.conejos.chirivella.a04_ejerapiusuarios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pablo.conejos.chirivella.a04_ejerapiusuarios.R;
import pablo.conejos.chirivella.a04_ejerapiusuarios.modelos.DataItem;

public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.UserVH> {



    private List<DataItem> objects;
    private Context context;
    private int resource;


    public UsersAdapters(List<DataItem> objects, Context context, int resource) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(context).inflate(resource,null);
        userView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new UserVH(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {

        DataItem user = objects.get(position);


        holder.lblNombre.setText(user.getFirstName());
        holder.lblEmail.setText(user.getEmail());

        Picasso.get()
                .load(user.getAvatar())//URL DE LA IMAGEN
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgAvatar);//IMAGEVIEW DONDE MOSTAR LA IMAGEN

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class UserVH extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView lblNombre;
        TextView lblEmail;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgUser_ModelView);
            lblEmail = itemView.findViewById(R.id.lblEmail_ViewHolder);
            lblNombre = itemView.findViewById(R.id.lblNombre_ModelView);
        }
    }
}
