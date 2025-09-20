package com.mpt.rua_java.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mpt.rua_java.R;
import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.presentation.util.CircularOutlineProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador para mostrar la lista de usuarios en RecyclerView
 * Muestra toda la información del usuario según requisitos:
 * nombre completo, email, teléfono, dirección, género, nacionalidad, imagen de perfil
 * Usa solución nativa para imágenes circulares sin dependencias externas
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users = new ArrayList<>();
    private OnUserClickListener listener;

    public interface OnUserClickListener {
        void onAddToContactsClick(User user);
        void onUserClick(User user);
        void onQRClick(User user);
    }

    public void setOnUserClickListener(OnUserClickListener listener) {
        this.listener = listener;
    }

    public void setUsers(List<User> users) {
        this.users = users != null ? users : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageProfile;
        private final TextView textName;
        private final TextView textEmail;
        private final TextView textPhone;
        private final TextView textCell;
        private final TextView textAddress;
        private final TextView textGender;
        private final TextView textNationality;
        private final TextView textAge;
        private final Button buttonAddToContacts;
        private final Button buttonShowQR;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.imageProfile);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPhone = itemView.findViewById(R.id.textPhone);
            textCell = itemView.findViewById(R.id.textCell);
            textAddress = itemView.findViewById(R.id.textAddress);
            textGender = itemView.findViewById(R.id.textGender);
            textNationality = itemView.findViewById(R.id.textNationality);
            textAge = itemView.findViewById(R.id.textAge);
            buttonAddToContacts = itemView.findViewById(R.id.buttonAddToContacts);
            buttonShowQR = itemView.findViewById(R.id.buttonShowQR);
            imageProfile.setOutlineProvider(new CircularOutlineProvider());
            imageProfile.setClipToOutline(true);
        }

        public void bind(User user) {
            // Cargar imagen de perfil con Glide usando transformación circular nativa
            Glide.with(itemView.getContext())
                .load(user.getPicture().getMedium())
                .apply(RequestOptions.centerCropTransform())
                .placeholder(R.drawable.ic_person_placeholder)
                .error(R.drawable.ic_person_placeholder)
                .into(imageProfile);

            // Mostrar toda la información del usuario
            textName.setText(user.getFullName());
            textEmail.setText(user.getEmail());
            textPhone.setText("📞 " + user.getPhone());
            textCell.setText("📱 " + user.getCell());
            textAddress.setText("🏠 " + user.getFullAddress());
            textGender.setText("⚥ " + user.getGender().substring(0, 1).toUpperCase() + user.getGender().substring(1));
            textNationality.setText("🌍 " + user.getNat());
            textAge.setText("🎂 " + user.getDob().getAge() + " años");

            // Configurar botones
            String contactButtonText = user.isAddedToContacts() ? "✓ En Contactos" : "+ Agregar a Contactos";
            buttonAddToContacts.setText(contactButtonText);
            buttonAddToContacts.setEnabled(!user.isAddedToContacts());

            // Listeners
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onUserClick(user);
            });

            buttonAddToContacts.setOnClickListener(v -> {
                if (listener != null) listener.onAddToContactsClick(user);
            });

            buttonShowQR.setOnClickListener(v -> {
                if (listener != null) listener.onQRClick(user);
            });
        }
    }
}
