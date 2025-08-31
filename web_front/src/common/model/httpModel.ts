
export interface HttpRequest {
    url: string;
    method: string;
    header : object | string;
    body : object | string;
}

export interface HttpResponse {
    status : number;
    headers : object | string;
    body : object | string;
    data : CommonResponse
}

export interface CommonResponse {
    responseCode : number;
    responseMessage : string;
}