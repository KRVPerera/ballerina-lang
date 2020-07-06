// Copyright (c) 2020 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

// Based on https://tools.ietf.org/html/rfc7234#section-4.2.4
function isAllowedToBeServedStale(RequestCacheControl? requestCacheControl, Response cachedResponse,
                                  boolean isSharedCache) returns boolean {
    // A cache MUST NOT generate a stale response if it is prohibited by an explicit in-protocol directive
    var responseCacheControl = cachedResponse.cacheControl;
    if (responseCacheControl is ResponseCacheControl) {
        if (isServingStaleProhibited(requestCacheControl, responseCacheControl)) {
            return false;
        }
    } else {
        return false;
    }
    return isStaleResponseAccepted(requestCacheControl, cachedResponse, isSharedCache);
}

// Based on https://tools.ietf.org/html/rfc7234#section-4.2.4
function isServingStaleProhibited(RequestCacheControl? reqCC, ResponseCacheControl resCC) returns boolean {
    // A cache MUST NOT generate a stale response if it is prohibited by an explicit in-protocol directive
    if (reqCC is RequestCacheControl) {
        if (reqCC.noCache || reqCC.noStore) {
            return true;
        }
    }

    if (resCC.mustRevalidate || resCC.proxyRevalidate || (resCC.sMaxAge >= 0)) {
        return true;
    }

    return false;
}

// Based on https://tools.ietf.org/html/rfc7234#section-4.2.4
function isStaleResponseAccepted(RequestCacheControl? requestCacheControl, Response cachedResponse,
                                 boolean isSharedCache) returns boolean {
    if (requestCacheControl is RequestCacheControl) {
        if (requestCacheControl.maxStale == MAX_STALE_ANY_AGE) {
            return true;
        } else if (requestCacheControl.maxStale >=
                               (getResponseAge(cachedResponse) - getFreshnessLifetime(cachedResponse, isSharedCache))) {
            return true;
        }
    }
    return false;
}
