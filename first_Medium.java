
import java.util.*;
import java.io.*;
public class first_medium {
        public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine().trim());
            String s[] = in.readLine().trim().split(" ");
            int time[] = new int[n];
            for (int i = 0; i < n; i++) {
                time[i] = Integer.parseInt(s[i]);
            }
            s = in.readLine().trim().split(" ");
            int weak[] = new int[n];
            for (int i = 0; i < n; i++) {
                weak[i] = Integer.parseInt(s[i]);
            }
            Solution ob = new Solution();
            int ans[] = ob.diagnosis(n, time, weak);
            for (int i : ans) {
                out.print(i + " ");
            }
            out.println();
        }
        out.close();
    }
}
class Solution {
    public int[] diagnosis(int n, int time[], int weak[]) {
        PriorityQueue<Patient> pq = new PriorityQueue<>(new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                if (p1.weakness == p2.weakness) {
                    return p1.index - p2.index;
                }
                return p1.weakness - p2.weakness;
            }
        });
        int[] leaveTimes = new int[n];
        int currentTime = 0;
        int patientIndex = 0;
        for (currentTime = 1; currentTime <= 1000006; currentTime++) {
            while (patientIndex < n && time[patientIndex] == currentTime) {
                pq.offer(new Patient(weak[patientIndex], patientIndex));
                patientIndex++;
            }
            if (!pq.isEmpty()) {
                Patient weakestPatient = pq.poll();
                int weakValue = weakestPatient.weakness;
                int idx = weakestPatient.index;
                leaveTimes[idx] = currentTime + weakValue;
                currentTime += weakValue - 1;
            }
        }
        return leaveTimes;
    }
}
class Patient {
    int weakness;
    int index;
    Patient(int weakness, int index) {
        this.weakness = weakness;
        this.index = index;
    }
}
