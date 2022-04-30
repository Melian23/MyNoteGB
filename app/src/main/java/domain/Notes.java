package domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };
    private String name;
    private String description;
    private String data;

    protected Notes(Parcel in) {
        name = in.readString();
        description = in.readString();
        data = in.readString();
    }

    public Notes(String name, String data, String description) {
        this.name = name;
        this.description = description;
        this.data = data;
    }

    public Notes(int i) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(data);
    }
}
