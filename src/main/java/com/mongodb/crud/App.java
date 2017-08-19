/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;

public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
//        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
//        MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27017)));
//        MongoClient client = new MongoClient(Collections.singletonList(new ServerAddress("localhost", 27017)));
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//        MongoClientOptions options = MongoClientOptions.builder().build();
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
//        MongoClient client = new MongoClient(new ServerAddress(), options);

//        MongoDatabase db = client.getDatabase("local");
        MongoDatabase db = client.getDatabase("local").withReadPreference(ReadPreference.secondary());

//        MongoCollection<Document> coll = db.getCollection("local");
        MongoCollection<BsonDocument> coll = db.getCollection("local", BsonDocument.class);
        System.out.println();


    }
}
