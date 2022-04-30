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
    private int data;

    protected Notes(Parcel in) {
        name = in.readString();
        description = in.readString();
        data = in.readInt();
    }

    public Notes(String name, int data, String description) {
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
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
        parcel.writeInt(data);
    }
}
