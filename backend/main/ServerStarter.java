import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;
import original.util.*;

public class ServerStarter{
  public static void main(String args[]){
    NormalPurchaseServer nps = new NormalPurchaseServer();
    ReservationConfirmServer rcfs = new ReservationConfirmServer();
    ReservedTicketingServer rtks = new ReservedTicketingServer();

    // Multi Threading begin
    nps.start();
    rcfs.start();
    rtks.start();
  }
}

