package com.example.pokemonwatchlist;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PokeModel implements Parcelable {    @SerializedName("name")
private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("weight")
    private int weight;
    @SerializedName("height")
    private int height;
    @SerializedName("base_experience")
    private int baseXP;
    @SerializedName("abilities")
    private List<AbilityEntry> abilities;
    @SerializedName("moves")
    private List<MoveEntry> moves;
    @SerializedName("sprites")
    private Sprites sprites;

    protected PokeModel(Parcel in) {
        name = in.readString();
        id = in.readInt();
        weight = in.readInt();
        height = in.readInt();
        baseXP = in.readInt();
        abilities = in.createTypedArrayList(AbilityEntry.CREATOR);
        moves = in.createTypedArrayList(MoveEntry.CREATOR);
        sprites = in.readParcelable(Sprites.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(weight);
        dest.writeInt(height);
        dest.writeInt(baseXP);
        dest.writeTypedList(abilities);
        dest.writeTypedList(moves);
        dest.writeParcelable(sprites, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PokeModel> CREATOR = new Creator<PokeModel>() {
        @Override
        public PokeModel createFromParcel(Parcel in) {
            return new PokeModel(in);
        }

        @Override
        public PokeModel[] newArray(int size) {
            return new PokeModel[size];
        }
    };

    public static class Sprites implements Parcelable {
        @SerializedName("other")
        private OtherSprites other;

        protected Sprites(Parcel in) {
            other = in.readParcelable(OtherSprites.class.getClassLoader());
        }

        public static final Creator<Sprites> CREATOR = new Creator<Sprites>() {
            @Override
            public Sprites createFromParcel(Parcel in) {
                return new Sprites(in);
            }

            @Override
            public Sprites[] newArray(int size) {
                return new Sprites[size];
            }
        };

        public String getOfficialArtwork() {
            if (other != null && other.officialArtwork != null) {
                return other.officialArtwork.frontDefault;
            }
            return null;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(other, flags);
        }
    }

    public static class OtherSprites implements Parcelable {
        @SerializedName("official-artwork")
        private OfficialArtwork officialArtwork;

        protected OtherSprites(Parcel in) {
            officialArtwork = in.readParcelable(OfficialArtwork.class.getClassLoader());
        }

        public static final Creator<OtherSprites> CREATOR = new Creator<OtherSprites>() {
            @Override
            public OtherSprites createFromParcel(Parcel in) {
                return new OtherSprites(in);
            }

            @Override
            public OtherSprites[] newArray(int size) {
                return new OtherSprites[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(officialArtwork, flags);
        }
    }

    public static class OfficialArtwork implements Parcelable {
        @SerializedName("front_default")
        private String frontDefault;

        protected OfficialArtwork(Parcel in) {
            frontDefault = in.readString();
        }

        public static final Creator<OfficialArtwork> CREATOR = new Creator<OfficialArtwork>() {
            @Override
            public OfficialArtwork createFromParcel(Parcel in) {
                return new OfficialArtwork(in);
            }

            @Override
            public OfficialArtwork[] newArray(int size) {
                return new OfficialArtwork[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(frontDefault);
        }
    }

    public String getOfficialArtwork() {
        if (sprites != null) {
            return sprites.getOfficialArtwork();
        }
        return null;
    }

    public static class AbilityEntry implements Parcelable {
        @SerializedName("ability")
        private Ability ability;

        protected AbilityEntry(Parcel in) {
            ability = in.readParcelable(Ability.class.getClassLoader());
        }

        public static final Creator<AbilityEntry> CREATOR = new Creator<AbilityEntry>() {
            @Override
            public AbilityEntry createFromParcel(Parcel in) {
                return new AbilityEntry(in);
            }

            @Override
            public AbilityEntry[] newArray(int size) {
                return new AbilityEntry[size];
            }
        };

        public Ability getAbility() {
            return ability;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(ability, flags);
        }
    }

    public static class Ability implements Parcelable {
        @SerializedName("name")
        private String name;

        protected Ability(Parcel in) {
            name = in.readString();
        }

        public static final Creator<Ability> CREATOR = new Creator<Ability>() {
            @Override
            public Ability createFromParcel(Parcel in) {
                return new Ability(in);
            }

            @Override
            public Ability[] newArray(int size) {
                return new Ability[size];
            }
        };

        public String getName() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
        }
    }

    public static class MoveEntry implements Parcelable {
        @SerializedName("move")
        private Move move;

        protected MoveEntry(Parcel in) {
            move = in.readParcelable(Move.class.getClassLoader());
        }

        public static final Creator<MoveEntry> CREATOR = new Creator<MoveEntry>() {
            @Override
            public MoveEntry createFromParcel(Parcel in) {
                return new MoveEntry(in);
            }

            @Override
            public MoveEntry[] newArray(int size) {
                return new MoveEntry[size];
            }
        };

        public Move getMove() {
            return move;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(move, flags);
        }
    }

    public static class Move implements Parcelable {
        @SerializedName("name")
        private String name;

        protected Move(Parcel in) {
            name = in.readString();
        }

        public static final Creator<Move> CREATOR = new Creator<Move>() {
            @Override
            public Move createFromParcel(Parcel in) {
                return new Move(in);
            }

            @Override
            public Move[] newArray(int size) {
                return new Move[size];
            }
        };

        public String getName() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
        }
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public int getWeight() { return weight; }
    public int getHeight() { return height; }
    public int getBaseXP() { return baseXP; }
    public List<AbilityEntry> getAbilities() { return abilities; }
    public List<MoveEntry> getMoves() { return moves; }
}