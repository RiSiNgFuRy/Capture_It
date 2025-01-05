//
//  BuildInfoProvider.swift
//  captureIt-ios
//
//  Created by Pranjal Gupta on 01/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum BuildTypes {
    case Release
    case Debug
}

@objc class BuildInfoProvider: NSObject {
    
    @objc static func getBuildInfo() -> [String: String] {
        
        let defaultConfigs = BuildInfoProvider().getDefaultConfigs()
                
        #if RELEASE
        return BuildInfoProvider().getReleaseBuildInfo(existingMap: defaultConfigs)
        #else
        return BuildInfoProvider().getDebugBuildInfo(existingMap: defaultConfigs)
        #endif
        
    }
    
    private func getDefaultConfigs() -> [String: String] {
        let baseUrl = "ndwe.in/huhvd"
        
        return [
            BuildInfoKey.BaseUrl.rawValue   : baseUrl
        ]
    }
    
    private func getReleaseBuildInfo(existingMap: [String: String]) -> [String: String] {
        return updateValues(in: existingMap, with: [
            BuildInfoKey.BuildType.rawValue : BuildTypes.Release.name.lowercased(),
        ])
    }
    
    private func getDebugBuildInfo(with existingMap: MutableMap<String, String>) -> [String: String] {
        return updateValues(in: existingMap, with: [
            BuildInfoKey.BuildType.rawValue : BuildTypes.Debug.name.lowercased(),
        ])
    }
    
    private func updateValues(in dictionary: [String: Any], with newValues: [String: Any]) -> [String: Any] {
        var updatedDictionary = dictionary
        updatedDictionary.merge(newValues) { (_, new) in new }
        return updatedDictionary
    }
}
