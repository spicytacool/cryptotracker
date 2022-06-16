import java.io.*;
import java.util.*;
import java.net.*;
import org.json.JSONObject;

class Main {
    public static void main(String[] args) throws IOException {
        
        // Class to take input values
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        
        // Types of crypto currencies
        String[] cryptoTypes = {"Bitcoin","Ethereum", "Litecoin", "Cardano", "Ripple", "Polkadot", "Dogecoin", "Solana", "Bitcoin Cash", "Stellar", "Monero", "EOS", "Kusama", "Bitcoin SV", "eCash", "Zcash", "Dash", "Mixin", "Groestlcoin"};
        
        System.out.println("Crypto Price Checker\n These are the following crypto details available");
        for(int i=1;i<=cryptoTypes.length;i++){
            System.out.println(i+". "+cryptoTypes[i-1]);
        }
        System.out.print("Enter the number associated with the crypto you want to get the information on - ");
        
        int val = Integer.parseInt(br.readLine());
        
        if(val < 1 || val > cryptoTypes.length)
        System.out.println("Oops! Invalid Input!");
        else{
            HttpURLConnection connection = null;

            // Try catch block in case of exceptions
        try {
            //Create connection
            URL url = new URL("https://api.blockchair.com/"+cryptoTypes[val-1].toLowerCase()+"/stats");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                connection.getInputStream()));
                                
                                
            String inputLine;
    
            while ((inputLine = in.readLine()) != null){
                JSONObject jsonObject = new JSONObject(inputLine);
                System.out.println("Market Price of "+cryptoTypes[val-1]+" is "+jsonObject.getJSONObject("data").getInt("market_price_usd")+" USD.");
            } 
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
              connection.disconnect();
            }
        }
        }

    }
}