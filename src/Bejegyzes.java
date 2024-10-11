import java.time.LocalDateTime;

public class Bejegyzes {
    private String szerzo;
    private String tartalom;
    private int likeok;
    private LocalDateTime letrejott;
    private LocalDateTime szerkesztve;

    public Bejegyzes(String tartalom, String szerzo) {
        this.tartalom = tartalom;
        this.szerzo = szerzo;
        this.likeok = 0;
        this.letrejott = LocalDateTime.now();
        this.szerkesztve = LocalDateTime.now();
    }

    public String getSzerzo(){
        return this.szerzo;
    }
    public String getTartalom(){
        return this.tartalom;
    }
    public void setTartalom(String tartalom){
        this.tartalom = tartalom;
        this.szerkesztve = LocalDateTime.now();
    }
    public int getLikeok(){
        return this.likeok;
    }
    public LocalDateTime getLetrejott(){
        return this.letrejott;
    }
    public LocalDateTime getSzerkesztve(){
        return this.szerkesztve;
    }
    public void Like(){
        this.likeok++;
    }

    @Override
    public String toString() {
        if(!szerkesztve.toString().equals(letrejott.toString())){
            return szerzo + " " + likeok + " " + letrejott +"\n Szerkesztve"+ szerkesztve + "\n" + tartalom+"\n";
        }
        else{
            return szerzo + " " + likeok + " " + letrejott +  "\n" + tartalom+"\n";
        }
    }
}
