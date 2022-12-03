package byow.Core4;

import java.util.ArrayList;

public class HashMap {
    private final java.util.HashMap<RoomCoordinates, ArrayList<RoomCoordinates>> hashMap;

    public HashMap(ArrayList<RoomCoordinates> rooms) {
        hashMap = new java.util.HashMap<>();
        for (RoomCoordinates room : rooms) {
            hashMap.put(room, new ArrayList<>());
        }
    }

//    public void connectRooms(ArrayList<RoomCoordinates> rooms) {
//        RoomCoordinates currRoom = null, minRoom = null;
//        int minWeight = Integer.MAX_VALUE, minDistance = Integer.MAX_VALUE;
//        for (int i = 0; i < rooms.size(); i++) {
//            currRoom = rooms.get(i);
//            for (RoomCoordinates wizPos : rooms) {
//                if (minRoom == null) {
//                    minRoom = wizPos;
//                }
//                else if (currRoom != wizPos) {
//                    if (distance(currRoom, wizPos) < minDistance && hashMap.get(wizPos).size() < minWeight) {
//                        minRoom = wizPos;
//                    }
//                }
//
//            }
//        }
//        hashMap.get(currRoom).add(minRoom);
//    }

    private int distance(RoomCoordinates rm1, RoomCoordinates rm2) {
        return (int) Math.sqrt(Math.pow(rm1.getMidDimmX() - rm2.getMidDimmX(), 2) + Math.pow(rm1.getMidDimmY() - rm2.getMidDimmY(), 2));
    }

    public void printHashMap(ArrayList<RoomCoordinates> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            System.out.print("Room " + i + "-> " + hashMap.get(rooms.get(i)));
            System.out.println();
        }
    }
}
