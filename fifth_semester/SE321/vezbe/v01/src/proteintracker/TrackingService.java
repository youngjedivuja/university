package proteintracker;

import exceptions.InvalidGoalException;

import java.util.ArrayList;
import java.util.List;

public class TrackingService {
    private int total;
    private int goal;
    private List<HistoryItem> historyItemList = new ArrayList<HistoryItem>();
    private int historyId = 0;

    public void addProtein(int amount){
        total += amount;
        historyItemList.add(new HistoryItem(historyId++, amount, "Add", total));
    }

    public void removeProtein(int amount){
        total-=amount;
        if(total < 0){
            total=0;
        }
        historyItemList.add(new HistoryItem(historyId, amount, "Sub", total));
    }

    public boolean isGoalMet(){
        return total == goal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int value) throws InvalidGoalException {
        if(value < 0){
            throw new InvalidGoalException("Goal is invalid");
        }
        this.goal = value;
    }

    public List<HistoryItem> getHistoryItemList() {
        return historyItemList;
    }
}
