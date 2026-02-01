export interface Feedback {
  id: string
  message: string
  author: string | null
  helpfulCount: number
}

export interface CreateFeedbackRequest {
  message: string
  author?: string | null
}
