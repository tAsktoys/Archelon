/*
 * Copyright(C) 2014 tAsktoys Project. All rights reserved.
 */
package com.tasktoys.archelon.data.entity;

/**
 *
 * @author mikanj
 * @author YuichiroSato
 */
public class OAuthAccount {
    
    private String id;
        private String accessToken;
        private String accessSecret;

        public OAuthAccount() {
            //
        }

        public OAuthAccount(String id, String accessToken, String accessSecret) {
            this.id = id;
            this.accessToken = accessToken;
            this.accessSecret = accessSecret;
        }

        public String getId() {
            return id;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getAccessSecret() {
            return accessSecret;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public void setAccessSecret(String accessSecret) {
            this.accessSecret = accessSecret;
        }
        
        public boolean validate() {
            if (accessToken == null || accessSecret == null)
                return false;
            return true;
        }
        
        public Object[] toObject() {
            return new Object[] {
                id, accessToken, accessSecret
            };
        }
}
