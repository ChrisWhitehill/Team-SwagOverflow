//
//  HttpClient.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import Foundation

let CONTENT_JSON = ["Content-Type": "application/json"]

enum HttpMethod: String {
    case GET = "GET"
    case POST = "POST"
    case PUT = "PUT"
    case DELETE = "DELETE"
}

class HttpClient {
    
    var domain: String
    
    init(domain: String) {
        self.domain = domain
    }
    
    func executeRequest(uri: String, method: HttpMethod, headers: Dictionary<String, String>?, postData: Dictionary<String, AnyObject>?, success: ((String)->())?, error: ((NSError?,String)->())?) {
        
        if (uri.isEmpty) {
            return
        }
        
        let url = NSURL(string: domain + uri)
        let request = NSMutableURLRequest(URL: url!)
        request.HTTPMethod = method.rawValue
        
        // set request body is necessary
        if let data = postData {
            do {
                request.HTTPBody = try NSJSONSerialization.dataWithJSONObject(data, options: NSJSONWritingOptions())
            } catch {
                return
            }
        }
        
        // set any custom headers passed in
        if let dict = headers {
            for (key, value) in dict {
                request.setValue(value, forHTTPHeaderField: key)
            }
        }
        
        let session = NSURLSession.sharedSession()
        
        let task = session.dataTaskWithRequest(request, completionHandler: { data, response, err in
            // if error thrown, call handler and quit
            if let e = err {
                error?(e, "An error occurred processing the request.")
                return
            }
            
            let resp = response as! NSHTTPURLResponse
            let respCode = resp.statusCode
            
            var message = ""
            if respCode < 300 { // 200s are good
                let dataString = NSString(data: data!, encoding: NSUTF8StringEncoding) as! String
                success?(dataString)
                return
            } else if respCode == 401 {
                message = "The request was unauthorized."
            } else if respCode < 500 { // 400s are bad
                message = "A server error occurred. Status code \(respCode)."
            } else { // 500s are also bad
                message = "A client error occurred. Status code \(respCode)."
            }
            
            error?(nil, message)
        })
        
        task.resume()
    }
}

func parseDictionaryFromJSON(data: String) -> [String: AnyObject]? {
    if let dict = parseJSON(data) as? [String: AnyObject] {
        return dict
    }
    
    return nil
}

func parseArrayFromJSON(data: String) -> [[String: AnyObject]]? {
    if let array = parseJSON(data) as? [[String: AnyObject]] {
        return array
    }
    
    return nil
}

func parseJSON(data: String) -> AnyObject? {
    if let encodedData = data.dataUsingEncoding(NSUTF8StringEncoding) {
        do {
            let json: AnyObject? = try NSJSONSerialization.JSONObjectWithData(encodedData, options: NSJSONReadingOptions.AllowFragments)
            return json
        } catch {
            return nil
        }
    }
    
    return nil
}
