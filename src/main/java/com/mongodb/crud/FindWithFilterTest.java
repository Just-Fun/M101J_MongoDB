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

import com.entity.Cello;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.m101j.util.Helpers.printJson;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("local");

        System.out.println();
        MongoIterable<String> strings = database.listCollectionNames();
        for (String string : strings) {
            System.out.println(string);
        }
        MongoCollection<Document> local = database.getCollection("local");
//        MongoCollection<Document> startup_log = database.getCollection("startup_log");

        List<Document> localAll = local.find().into(new ArrayList<Document>());
        for (Document cur : localAll) {
            printJson(cur);
        }

        System.out.println("---------------");

       /* Cello cello = Cello.builder()
                .bow("some")
                .spire("long")
                .build();

        Gson gson = new Gson();
        String celloJson = gson.toJson(cello);

        Document celloIn = new Document("cello", celloJson);
        local.insertOne(celloIn);*/

       /* for (Document cur : localAll) {
            printJson(cur);
        }*/

        List<Document> newFind = local.find().into(new ArrayList<Document>());

        Document document = newFind.get(2);
        Object cello = document.get("cello");
//        Cello cello = document.get("cello", Cello.class);
        System.out.println("cello: " + cello);

        Gson gson2 = new GsonBuilder().create();
        Cello celloFromDB = gson2.fromJson(cello.toString(), Cello.class);
        System.out.println(celloFromDB);



       /* JacksonDBCollection<Cello, String> coll = JacksonDBCollection.wrap(local, Cello.class,
                String.class);
//        MyObject myObject = ...
        WriteResult<MyObject, String> result = coll.insert(myObject);
        String id = result.getSavedId();
        MyObject savedObject = coll.findOneById(id);*/

        System.out.println();

//        MongoCollection<Document> collection = database.getCollection("findWithFilterTest");
//        collection.drop();

        // insert 10 documents with two random integers
       /* for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                                 .append("x", new Random().nextInt(2))
                                 .append("y", new Random().nextInt(100)));
        }*/

//        Bson filter = new Document("x", 0)
//        .append("y", new Document("$gt", 10).append("$lt", 90));

//        Bson filter = Filters.eq("x", 0);

   /*     Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        List<Document> all = collection.find(filter).into(new ArrayList<Document>());

        for (Document cur : all) {
            printJson(cur);
        }

        long count = collection.count(filter);
        System.out.println();
        System.out.println(count);*/
    }
}
